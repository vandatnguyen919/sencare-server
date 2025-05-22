package org.entrepremium.sencare.features.myuser;

import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public MyUser findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public MyUser save(MyUser newUser) {
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public MyUser update(Integer userId, MyUser user) {
        return userRepository.findById(userId)
                .map(oldUser -> {
                    oldUser.setEmail(user.getEmail());
                    oldUser.setEnabled(user.isEnabled());
                    oldUser.setRoles(user.getRoles());
                    return userRepository.save(oldUser);
                })
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }

    public void delete(Integer userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username) // First, we need to find this user from database.
                .map(MyUserPrincipal::new)  // If found, wrap the returned user instance in a MyUserPrinciple instance.
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found.")); // Otherwise, throw an exception.
    }
}

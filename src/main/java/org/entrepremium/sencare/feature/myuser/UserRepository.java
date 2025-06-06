package org.entrepremium.sencare.feature.myuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, String> {

    Optional<MyUser> findByEmail(String email);
}

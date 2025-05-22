package org.entrepremium.sencare.features.myuser;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "my_user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private String id;
    private String email;
    private String password;
    private boolean enabled;
    private String roles;
}

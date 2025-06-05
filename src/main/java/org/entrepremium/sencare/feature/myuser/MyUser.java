package org.entrepremium.sencare.feature.myuser;

import jakarta.persistence.*;
import lombok.Data;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.review.Review;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "my_user")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean enabled;

    private String roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hospital> ownedHospitals = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Review> reviews = new ArrayList<>();
}

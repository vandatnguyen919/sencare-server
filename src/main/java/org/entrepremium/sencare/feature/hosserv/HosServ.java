package org.entrepremium.sencare.feature.hosserv;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.entrepremium.sencare.feature.doctorsystem.doctor.Doctor;
import org.entrepremium.sencare.feature.hospitalsystem.hospital.Hospital;
import org.entrepremium.sencare.feature.review.Review;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "hospital_service")
public class HosServ {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hosserv_id", updatable = false, nullable = false)
    private String id;

    private String servImage;

    private String servName;

    private String servDesc;

    private double servPrice;

    private boolean available;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "hosServ", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}

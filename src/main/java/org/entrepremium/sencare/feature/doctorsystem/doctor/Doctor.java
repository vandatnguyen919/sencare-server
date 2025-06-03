package org.entrepremium.sencare.feature.doctorsystem.doctor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctorsystem.education.Education;
import org.entrepremium.sencare.feature.doctorsystem.workexperience.WorkExperience;
import org.entrepremium.sencare.feature.hospitalsystem.hospital.Hospital;
import org.entrepremium.sencare.feature.review.Review;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String doctorId;

    private String doctorAvatar;
    private String doctorName;
    private String doctorDescription;
    private BigDecimal doctorPrice;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<WorkExperience> workExperiences = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
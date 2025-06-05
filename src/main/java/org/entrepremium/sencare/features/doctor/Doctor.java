package org.entrepremium.sencare.features.doctor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.features.education.Education;
import org.entrepremium.sencare.features.hospital.Hospital;
import org.entrepremium.sencare.features.workexperience.WorkExperience;

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

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<WorkExperience> workExperiences = new ArrayList<>();
}
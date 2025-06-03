package org.entrepremium.sencare.features.doctorsystem.workexperience;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.features.doctorsystem.doctor.Doctor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String wexId;

    private String hospitalName;
    private String jobTitle;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
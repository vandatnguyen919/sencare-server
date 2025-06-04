package org.entrepremium.sencare.features.education;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.features.doctor.Doctor;

@Entity
@Data
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String eduId;

    private String collegeName;
    private String description;
    private String issuedBy;
    private Integer issuedYear;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

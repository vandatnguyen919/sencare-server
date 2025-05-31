package org.entrepremium.sencare.features.hospitalsystem.hospitalspec;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.features.hospitalsystem.hospital.Hospital;
import org.entrepremium.sencare.features.hospitalsystem.specialization.Specialization;

@Entity
@Data
@NoArgsConstructor
public class HospitalSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Specialization specialization;
}
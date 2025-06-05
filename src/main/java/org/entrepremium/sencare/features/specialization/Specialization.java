package org.entrepremium.sencare.features.specialization;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.features.hospitalspec.HospitalSpec;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String specId;

    private String specName;
    private String specDescription;

    @OneToMany(mappedBy = "specialization", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<HospitalSpec> hospitalSpecs = new ArrayList<>();
}
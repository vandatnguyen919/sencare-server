package org.entrepremium.sencare.feature.hospital;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.spec.Specialization;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hospitalId;

    private String hospitalAvatar;
    private String hospitalName;
    private String hospitalDescription;
    private String hospitalPhone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "hospital_spec",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "spec_id")
    )
    private List<Specialization> specializations = new ArrayList<>();

    public void addSpec(Specialization specialization) {
        this.specializations.add(specialization);
        specialization.getHospitals().add(this);
    }

    public void addAllSpecs(List<Specialization> specializations) {
        specializations.forEach(spec -> spec.addHospital(this));
        this.specializations.addAll(specializations);
    }

    public void removeSpec(Specialization specialization) {
        this.specializations.remove(specialization);
        specialization.getHospitals().remove(this);
    }
}
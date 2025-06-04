package org.entrepremium.sencare.feature.hospital;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hospitalspec.HospitalSpec;
import org.entrepremium.sencare.feature.myuser.MyUser;

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

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<HospitalSpec> hospitalSpecs = new ArrayList<>();
}
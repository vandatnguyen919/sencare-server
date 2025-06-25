package org.entrepremium.sencare.feature.hospital;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.specialization.Specialization;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "hospital_specialization",
            joinColumns = @JoinColumn(name = "hospital_id"),
            inverseJoinColumns = @JoinColumn(name = "spec_id")
    )
    private List<Specialization> specializations = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<HosServ> hosServs = new ArrayList<>();
}
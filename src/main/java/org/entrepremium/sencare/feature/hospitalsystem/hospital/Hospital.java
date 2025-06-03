package org.entrepremium.sencare.feature.hospitalsystem.hospital;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctorsystem.doctor.Doctor;
import org.entrepremium.sencare.feature.hospitalsystem.hospitalspec.HospitalSpec;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.review.Review;

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

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<HosServ> services = new ArrayList<>();
}
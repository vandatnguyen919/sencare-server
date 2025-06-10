package org.entrepremium.sencare.feature.appointment;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.timeslot.Timeslot;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "appointment_id", updatable = false, nullable = false)
    private String id;

    private String description;
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MyUser user;

    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Timeslot> timeslots = new ArrayList<>();
}
package org.entrepremium.sencare.feature.timeslot;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.appointment.Appointment;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hosserv.HosServ;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "timeslot_id", updatable = false, nullable = false)
    private String id;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private boolean isOccupied;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hosserv_id")
    private HosServ hosServ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
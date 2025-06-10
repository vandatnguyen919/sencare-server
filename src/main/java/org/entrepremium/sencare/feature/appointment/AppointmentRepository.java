package org.entrepremium.sencare.feature.appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    Page<Appointment> findAll(Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.status = :status")
    List<Appointment> findByStatus(@Param("status") String status);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.doctorId = :doctorId")
    List<Appointment> findByDoctorId(@Param("doctorId") String doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.user.id = :userId")
    List<Appointment> findByUserId(@Param("userId") String userId);
}

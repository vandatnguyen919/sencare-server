package org.entrepremium.sencare.feature.timeslot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeslotRepository extends JpaRepository<Timeslot, String> {

    Page<Timeslot> findAll(Pageable pageable);

    @Query("SELECT t FROM Timeslot t WHERE t.doctor.doctorId = :doctorId")
    List<Timeslot> findByDoctorId(@Param("doctorId") String doctorId);

    @Query("SELECT t FROM Timeslot t WHERE t.date = :date")
    List<Timeslot> findByDate(@Param("date") LocalDate date);

    @Query("SELECT t FROM Timeslot t WHERE t.isOccupied = :isOccupied")
    List<Timeslot> findByOccupiedStatus(@Param("isOccupied") boolean isOccupied);

    @Query("SELECT t FROM Timeslot t WHERE t.doctor.doctorId = :doctorId AND t.date = :date")
    List<Timeslot> findByDoctorIdAndDate(@Param("doctorId") String doctorId, @Param("date") LocalDate date);
}

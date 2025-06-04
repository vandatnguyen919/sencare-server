package org.entrepremium.sencare.feature.review;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findByHospital(Pageable pageable, Hospital hospital);
    Page<Review> findByDoctor(Pageable pageable, Doctor doctor);
    Page<Review> findByHosServ(Pageable pageable, HosServ hosServ);
}

package org.entrepremium.sencare.features.hospitalsystem.hospitalspec;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalSpecRepository extends JpaRepository<HospitalSpec, String> {
    List<HospitalSpec> findByHospitalHospitalId(String hospitalId);
    List<HospitalSpec> findBySpecializationSpecId(String specId);

    @Query("SELECT hs FROM HospitalSpec hs WHERE hs.hospital.hospitalName LIKE %:hospitalName%")
    List<HospitalSpec> findByHospitalName(@Param("hospitalName") String hospitalName);

    @Query("SELECT hs FROM HospitalSpec hs WHERE hs.specialization.specName LIKE %:specName%")
    List<HospitalSpec> findBySpecializationName(@Param("specName") String specName);

    Page<HospitalSpec> findAll(Pageable pageable);
}

package org.entrepremium.sencare.features.specialization;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, String> {
    List<Specialization> findBySpecNameContaining(String name);
    Page<Specialization> findAll(Pageable pageable);
}

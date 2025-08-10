package org.entrepremium.sencare.feature.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser_IdOrderByCreatedAtDesc(String userId);

    List<Order> findByStatusOrderByCreatedAtDesc(Order.OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.doctor.doctorId = :doctorId ORDER BY o.createdAt DESC")
    List<Order> findByDoctorIdOrderByCreatedAtDesc(@Param("doctorId") String doctorId);

    @Query("SELECT o FROM Order o WHERE o.hosServ.id = :hosServId ORDER BY o.createdAt DESC")
    List<Order> findByHosServIdOrderByCreatedAtDesc(@Param("hosServId") String hosServId);
}

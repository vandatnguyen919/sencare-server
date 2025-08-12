package org.entrepremium.sencare.feature.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser_IdOrderByCreatedAtDesc(String userId);

    List<Order> findByStatusOrderByCreatedAtDesc(Order.OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.doctor.doctorId = :doctorId ORDER BY o.createdAt DESC")
    List<Order> findByDoctorIdOrderByCreatedAtDesc(@Param("doctorId") String doctorId);

    @Query("SELECT o FROM Order o WHERE o.hosServ.id = :hosServId ORDER BY o.createdAt DESC")
    List<Order> findByHosServIdOrderByCreatedAtDesc(@Param("hosServId") String hosServId);

     long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

     @Query("SELECT COALESCE(SUM(o.price), 0) FROM Order o WHERE o.status = :status AND o.createdAt BETWEEN :start AND :end")
     Double sumRevenueByStatusAndCreatedAtBetween(@Param("status") Order.OrderStatus status,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

     @Query("SELECT o.status AS status, COUNT(o) AS count FROM Order o WHERE o.createdAt BETWEEN :start AND :end GROUP BY o.status")
     List<Object[]> countGroupByStatusBetween(@Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end);

    @Query("""
    SELECT COUNT(o) 
    FROM Order o
    WHERE o.hosServ IS NOT NULL 
      AND o.createdAt BETWEEN :start AND :end
""")
    long countServicesBookedBetween(LocalDateTime start, LocalDateTime end);
}

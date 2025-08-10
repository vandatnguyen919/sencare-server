package org.entrepremium.sencare.feature.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.entrepremium.sencare.feature.order.Order;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDto {

    private String orderId;

    private String servUser;

    private String servName;

    private double price;

    private String appointmentTime;

    private String fullName;

    private String birthDate;

    private String gender;

    private String phone;

    private String address;

    private String reason;

    private String paymentMethod;

    private Order.OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

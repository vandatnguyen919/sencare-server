package org.entrepremium.sencare.feature.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDto {

    private String servId; // (doctorId or hosServId)

    private String servUser;

    private String appointmentTime;

    private String fullName;

    private String birthDate;

    private String gender;

    private String phone;

    private String address;

    private String reason;

    private String paymentMethod;
}

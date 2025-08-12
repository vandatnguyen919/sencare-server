package org.entrepremium.sencare.feature.order.dto;

import lombok.Data;
import org.entrepremium.sencare.feature.order.Order;

@Data
public class UpdateOrderStatusDto {
    private Order.OrderStatus status;
}

package org.entrepremium.sencare.feature.order.converter;

import org.entrepremium.sencare.feature.order.Order;
import org.entrepremium.sencare.feature.order.dto.OrderDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {

    public OrderDto convert(@NonNull Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setServUser(order.getServUser());
        dto.setServName(order.getServName());
        dto.setPrice(order.getPrice());
        dto.setAppointmentTime(order.getAppointmentTime());
        dto.setFullName(order.getFullName());
        dto.setBirthDate(order.getBirthDate());
        dto.setGender(order.getGender());
        dto.setPhone(order.getPhone());
        dto.setAddress(order.getAddress());
        dto.setReason(order.getReason());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        return dto;
    }
}
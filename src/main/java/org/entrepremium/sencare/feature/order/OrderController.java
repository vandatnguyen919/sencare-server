package org.entrepremium.sencare.feature.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entrepremium.sencare.feature.order.converter.OrderToOrderDtoConverter;
import org.entrepremium.sencare.feature.order.dto.CreateOrderDto;
import org.entrepremium.sencare.feature.order.dto.OrderDto;
import org.entrepremium.sencare.feature.order.dto.UpdateAppointmentTimeDto;
import org.entrepremium.sencare.feature.order.dto.UpdateOrderStatusDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.entrepremium.sencare.system.util.JwtUtils;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final OrderToOrderDtoConverter orderToOrderDtoConverter;

    @PostMapping
    public Result createOrder(
            @RequestBody CreateOrderDto createOrderDto,
            JwtAuthenticationToken jwtAuthenticationToken
    ) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        log.info("Creating order for user: {}", userId);

        Order order = orderService.createOrder(userId, createOrderDto);
        OrderDto orderDto = orderToOrderDtoConverter.convert(order);
        return new Result(true, StatusCode.SUCCESS, "Add Success", orderDto);
    }

    @GetMapping("/me")
    public Result getMyOrders(JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        log.info("Fetching orders for user: {}", userId);

        List<Order> orders = orderService.getUserOrders(userId);
        List<OrderDto> orderDtos = orders.stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", orderDtos);
    }

    @GetMapping("/{orderId}")
    public Result getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderDto orderDto = orderToOrderDtoConverter.convert(order);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", orderDto);
    }

    @GetMapping
    public Result getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDto> orderDtos = orders.stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", orderDtos);
    }

    @PatchMapping("/{orderId}/status")
    public Result updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody UpdateOrderStatusDto updateOrderStatusDto) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, updateOrderStatusDto.getStatus());
        OrderDto orderDto = orderToOrderDtoConverter.convert(updatedOrder);
        return new Result(true, StatusCode.SUCCESS, "Update Success", orderDto);
    }

    @PatchMapping("/{orderId}/aptm-time")
    public Result updateAppointmentTime(
            @PathVariable String orderId,
            @RequestBody UpdateAppointmentTimeDto updateAppointmentTimeDto) {
        log.info("Updating appointment time for order: {}", orderId);

        Order updatedOrder = orderService.updateAppointmentTime(orderId, updateAppointmentTimeDto.getAppointmentTime());
        OrderDto orderDto = orderToOrderDtoConverter.convert(updatedOrder);
        return new Result(true, StatusCode.SUCCESS, "Update Success", orderDto);
    }
}

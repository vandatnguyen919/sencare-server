package org.entrepremium.sencare.feature.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entrepremium.sencare.feature.order.converter.OrderToOrderDtoConverter;
import org.entrepremium.sencare.feature.order.dto.CreateOrderDto;
import org.entrepremium.sencare.feature.order.dto.OrderDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result> createOrder(
            @RequestBody CreateOrderDto createOrderDto,
            JwtAuthenticationToken jwtAuthenticationToken
    ) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        log.info("Creating order for user: {}", userId);

        Order order = orderService.createOrder(userId, createOrderDto);
        OrderDto orderDto = orderToOrderDtoConverter.convert(order);
        Result result = new Result(true, HttpStatus.CREATED.value(), "Add Success", orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/me")
    public ResponseEntity<Result> getMyOrders(JwtAuthenticationToken jwtAuthenticationToken) {
        String userId = JwtUtils.getUserId(jwtAuthenticationToken);
        log.info("Fetching orders for user: {}", userId);

        List<Order> orders = orderService.getUserOrders(userId);
        List<OrderDto> orderDtos = orders.stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
        Result result = new Result(true, HttpStatus.OK.value(), "Find All Success", orderDtos);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Result> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        OrderDto orderDto = orderToOrderDtoConverter.convert(order);
        Result result = new Result(true, HttpStatus.OK.value(), "Find One Success", orderDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Result> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDto> orderDtos = orders.stream()
                .map(orderToOrderDtoConverter::convert)
                .collect(Collectors.toList());
        Result result = new Result(true, HttpStatus.OK.value(), "Find All Success", orderDtos);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Result> updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam Order.OrderStatus status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        OrderDto orderDto = orderToOrderDtoConverter.convert(updatedOrder);
        Result result = new Result(true, HttpStatus.OK.value(), "Update Success", orderDto);
        return ResponseEntity.ok(result);
    }
}

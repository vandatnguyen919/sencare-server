package org.entrepremium.sencare.feature.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.DoctorRepository;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hosserv.HosServRepository;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.UserRepository;
import org.entrepremium.sencare.feature.order.dto.CreateOrderDto;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final DoctorRepository doctorRepository;

    private final HosServRepository hosServRepository;

    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(String userId, CreateOrderDto createOrderDto) {
        log.info("Creating order for user: {} with service ID: {}", userId, createOrderDto.getServId());

        // Get user
        MyUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User", userId));

        // Asynchronously find both doctor and hospital service
        CompletableFuture<Optional<Doctor>> doctorFuture = findDoctorAsync(createOrderDto.getServId());
        CompletableFuture<Optional<HosServ>> hosServFuture = findHosServAsync(createOrderDto.getServId());

        // Wait for both async operations to complete
        CompletableFuture.allOf(doctorFuture, hosServFuture).join();

        Optional<Doctor> doctorOpt;
        Optional<HosServ> hosServOpt;

        try {
            doctorOpt = doctorFuture.get();
            hosServOpt = hosServFuture.get();
        } catch (Exception e) {
            log.error("Error creating order", e);
            throw new RuntimeException("Failed to create order", e);
        }
        Order order = new Order();
        order.setUser(user);
        order.setServUser(createOrderDto.getServUser());
        order.setAppointmentTime(createOrderDto.getAppointmentTime());
        order.setFullName(createOrderDto.getFullName());
        order.setBirthDate(createOrderDto.getBirthDate());
        order.setGender(createOrderDto.getGender());
        order.setPhone(createOrderDto.getPhone());
        order.setAddress(createOrderDto.getAddress());
        order.setReason(createOrderDto.getReason());
        order.setPaymentMethod(createOrderDto.getPaymentMethod());
        order.setStatus(Order.OrderStatus.PENDING);

        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            order.setDoctor(doctor);
            order.setServName("Doctor Service - " + doctor.getDoctorName());
            order.setPrice(doctor.getDoctorPrice().doubleValue());

        } else if (hosServOpt.isPresent()) {
            HosServ hosServ = hosServOpt.get();
            order.setHosServ(hosServ);
            order.setServName("Hospital Service - " + hosServ.getServName());
            order.setPrice(hosServ.getServPrice());

        } else {
            throw new ObjectNotFoundException("Service", createOrderDto.getServId());
        }

        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully with id: {}", savedOrder.getOrderId());

        return savedOrder;
    }

    @Async
    public CompletableFuture<Optional<Doctor>> findDoctorAsync(String serviceId) {
        log.debug("Searching for doctor with id: {}", serviceId);
        return CompletableFuture.completedFuture(doctorRepository.findById(serviceId));
    }

    @Async
    public CompletableFuture<Optional<HosServ>> findHosServAsync(String serviceId) {
        log.debug("Searching for hospital service with id: {}", serviceId);
        return CompletableFuture.completedFuture(hosServRepository.findById(serviceId));
    }

    public List<Order> getUserOrders(String userId) {
        log.info("Fetching orders for user: {}", userId);
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(userId);
    }

    public Order getOrderById(String orderId) {
        log.info("Fetching order with id: {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order", orderId));
    }

    public List<Order> getAllOrders() {
        log.info("Fetching all orders");
        return orderRepository.findAll();
    }

    @Transactional
    public Order updateOrderStatus(String orderId, Order.OrderStatus status) {
        log.info("Updating order status for order: {} to status: {}", orderId, status);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order", orderId));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}

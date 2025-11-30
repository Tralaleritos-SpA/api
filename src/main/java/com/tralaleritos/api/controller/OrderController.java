package com.tralaleritos.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tralaleritos.api.DTO.OrderStatusUpdateDTO;

import com.tralaleritos.api.DTO.OrderRequestDTO;
import com.tralaleritos.api.model.Order;
import com.tralaleritos.api.service.OrderService;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint 1: POST /api/v1/orders (Creación del pedido)
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO request) {
        try {
            Optional<Order> orderOptional = orderService.createOrder(request);

            if (orderOptional.isPresent()) {
                return new ResponseEntity<>(orderOptional.get(), HttpStatus.CREATED); // 201 CREATED
            } else {
                return new ResponseEntity<>("Usuario o uno o más productos no encontrados.", HttpStatus.NOT_FOUND); // 404
                                                                                                                    // Not
                                                                                                                    // Found
            }

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request

        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // Endpoint 2: GET /api/v1/orders/{id} (Consulta de detalle de un solo pedido)
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable UUID id) {
        Optional<Order> orderOptional = orderService.findOrderById(id);

        if (orderOptional.isPresent()) {
            return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("Pedido con ID " + id + " no encontrado.", HttpStatus.NOT_FOUND); // 404 Not
                                                                                                          // Found
        }
    }

    /// (Consulta de TODOS los pedidos de un usuario)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable UUID userId) {
        List<Order> orders = orderService.findAllOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK); // 200 OK
    }

    // (Actualizar estado del pedido)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable UUID id, @RequestBody OrderStatusUpdateDTO request) {
        Optional<Order> updatedOrder = orderService.updateOrderStatus(id, request.getStatus());

        if (updatedOrder.isPresent()) {
            return new ResponseEntity<>(updatedOrder.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("Pedido con ID " + id + " no encontrado.", HttpStatus.NOT_FOUND); // 404
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Endpoint 4: GET /api/v1/orders (Consulta de TODOS los pedidos)
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.findAllOrders();

        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint 5: PUT /api/v1/orders/{id} (Actualizar un pedido)
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID id, @RequestBody Order orderDetails) {
        if (!id.equals(orderDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order updated = orderService.updateOrder(orderDetails);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Endpoint 6: DELETE /api/v1/orders/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
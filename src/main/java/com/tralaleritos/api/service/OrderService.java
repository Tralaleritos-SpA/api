package com.tralaleritos.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.DTO.CartItemDTO;
import com.tralaleritos.api.DTO.OrderRequestDTO;
import com.tralaleritos.api.model.Order;
import com.tralaleritos.api.model.OrderItem;
import com.tralaleritos.api.model.Product;
import com.tralaleritos.api.model.User;
import com.tralaleritos.api.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, ProductService productService, UserService userService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
    }

    // MÉTODO 1: Consulta de Pedido por ID (Para detalles)
    public Optional<Order> findOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    // MÉTODO 2: Consulta de Pedidos por User ID (Para "Mis Pedidos")
    public List<Order> findAllOrdersByUserId(UUID userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    /**
     * Crea una orden.
     */
    public Optional<Order> createOrder(OrderRequestDTO request) {

        Optional<User> user = userService.findUserById(request.getUserId());
        if (user.isEmpty()) {
            return Optional.empty();
        }

        Order newOrder = new Order();
        newOrder.setUser(user.get());

        // --- Mapeo de CAMPOS DE ENVÍO desde DTO al Modelo ---
        newOrder.setFullName(request.getFullName());
        newOrder.setPhone(request.getPhone());
        newOrder.setShippingAddress(request.getShippingAddress());
        newOrder.setShippingCity(request.getShippingCity());
        newOrder.setShippingZip(request.getShippingZip());
        // ---------------------------------------------------

        List<OrderItem> orderItems = new ArrayList<>();
        int finalTotal = 0;

        for (CartItemDTO itemDTO : request.getItems()) {
            Optional<Product> productOptional = productService.findProductById(itemDTO.getProductId());
            if (productOptional.isEmpty()) {
                return Optional.empty();
            }
            Product product = productOptional.get();

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }

            OrderItem item = new OrderItem();
            item.setOrder(newOrder);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(product.getPrice());
            item.setSubTotal(product.getPrice() * itemDTO.getQuantity());

            orderItems.add(item);
            finalTotal += item.getSubTotal();

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productService.saveProduct(product);
        }

        int shippingFee = request.getShippingFee(); // Obtiene el valor 5000 del DTO
        newOrder.setShippingFee(shippingFee); // Asume que Order.java tiene este setter
        finalTotal += shippingFee;

        newOrder.setTotal_price(finalTotal);
        newOrder.setItems(orderItems);

        return Optional.of(orderRepository.save(newOrder));
    }

    // MÉTODO 3: Consulta de TODOS los pedidos
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // MÉTODO 4: Actualizar pedido (simple passthrough to repository)
    public Order updateOrder(Order orderDetails) {
        return orderRepository.save(orderDetails);
    }

    // MÉTODO 5: Eliminar pedido
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
package org.example.gc_coffee.order.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.global.configure.aop.TimeTrace;
import org.example.gc_coffee.global.exception.BadRequestException;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.repository.OrderRepository;
import org.example.gc_coffee.order.requestDto.CreateOrderRequestDto;
import org.example.gc_coffee.order.responseDto.OrderResponseDto;
import org.example.gc_coffee.order.type.OrderStatus;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.example.gc_coffee.orderItem.requestDto.CreateOrderItemRequestDto;
import org.example.gc_coffee.orderItem.responseDto.OrderItemResponse;
import org.example.gc_coffee.orderItem.service.OrderItemService;
import org.example.gc_coffee.product.entity.Product;
import org.example.gc_coffee.product.repository.ProductRepository;
import org.example.gc_coffee.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_ORDER_ID;
import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_PRODUCT_ID;

@RequiredArgsConstructor
@Service
public class OrderSerivce {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ProductService productService;


    //주문 등록 메서드
    @TimeTrace
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {

        Order order = requestDto.toEntity();

        orderRepository.save(order);



        //orderItems 응답값을 담을 리스트
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        //request의 item들을 팩토리 메서드로 객체화
        for (CreateOrderItemRequestDto itemDto : requestDto.getItems()) {

            Product product = productService.getProductById(itemDto.getProductId());

            //메서드 안에서 saveAll을 쓴다면?
            orderItemService.createOrderItem(itemDto.of(product, order));

            orderItemResponses.add(OrderItemResponse.from(itemDto.of(product, order)));
        }


        return OrderResponseDto.of(order, orderItemResponses);
    }


    //주문 단건 조회
    public OrderResponseDto getOrder(UUID orderId) {

        Order order = findOrderById(orderId);

        return OrderResponseDto.from(order);
    }

    //PK를 통한 주문 검색
    public Order findOrderById(UUID orderId) {

        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_ORDER_ID));
    }


    //주문 삭제 메서드
    public void deleteOrder(UUID orderId) {

        Order order = findOrderById(orderId);

        orderRepository.deleteById(order.getOrderId());
    }


    public void updateOrderStatus(LocalDateTime startOfDay, LocalDateTime endOfDay) {

        orderRepository.updateOrderStatus(OrderStatus.SHIPPED, startOfDay, endOfDay);
    }
}

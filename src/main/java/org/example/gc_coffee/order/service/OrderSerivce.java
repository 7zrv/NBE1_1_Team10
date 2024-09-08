package org.example.gc_coffee.order.service;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @Transactional(rollbackOn = BadRequestException.class)
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {

        Order order = requestDto.toEntity();

        orderRepository.save(order);

        //팩토리 메서드로 생성될 orderItems 객체를 넣을 리스트
        List<OrderItem> orderItems = new ArrayList<>();

        //orderItems 반환에 이용할 response를 담을객체
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        //request의 item들을 팩토리 메서드로 객체화
        for (CreateOrderItemRequestDto itemDto : requestDto.getItems()) {

            Product product = productService.getProductById(itemDto.getProductId());
            orderItems.add(itemDto.of(product, order));
        }

        //객체화된 item들을 저장
        for (OrderItem orderItem : orderItems) {

            orderItemService.createOrderItem(orderItem);
            orderItemResponses.add(OrderItemResponse.from(orderItem));
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

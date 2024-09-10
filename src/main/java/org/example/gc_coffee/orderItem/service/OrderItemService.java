package org.example.gc_coffee.orderItem.service;


import lombok.RequiredArgsConstructor;

import org.example.gc_coffee.global.exception.BadRequestException;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.example.gc_coffee.orderItem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_ITEM_ID;
import static org.example.gc_coffee.global.exception.ExceptionCode.NOT_FOUND_ORDER_ID;


@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    //주문된 상품 저장 메서드
    public void createOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    // orderId를 파라미터로 하면 findByOrderId가 컴파일 에러가 발생함
    // Order 객체로 검색시 에러해결됨 왜 안되지
    public List<OrderItem> getOrderItemById(Order order) {

        return orderItemRepository.findByOrder(order)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_ITEM_ID));
    }

    public void createOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}

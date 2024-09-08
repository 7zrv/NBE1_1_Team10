package org.example.gc_coffee.order.responseDto;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.type.OrderStatus;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.example.gc_coffee.orderItem.responseDto.OrderItemResponse;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class OrderResponseDto {

    private final UUID orderId;

    private final String email;

    private final String address;

    private final String postcode;

    private final OrderStatus orderStatus;

    private final List<OrderItemResponse> orderItems;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order.getOrderId(), order.getEmail(), order.getAddress(), order.getPostcode(), order.getOrderStatus(), null);
    }

    public static OrderResponseDto of(Order order, List<OrderItemResponse> orderItems) {
        return new OrderResponseDto(order.getOrderId(), order.getEmail(), order.getAddress(), order.getPostcode(), order.getOrderStatus(), orderItems);
    }

}

package org.example.gc_coffee.order.responseDto;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.order.type.OrderStatus;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class OrderResponseDto {


    private final UUID orderId;

    private final String email;

    private final String address;

    private final String postcode;

    private final OrderStatus orderStatus;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order.getOrderId(), order.getEmail(), order.getAddress(), order.getPostcode(), order.getOrderStatus());
    }

}

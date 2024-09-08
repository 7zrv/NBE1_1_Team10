package org.example.gc_coffee.orderItem.responseDto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.example.gc_coffee.product.entity.Product;

@RequiredArgsConstructor
@Getter
public class OrderItemResponse {

    private final String productName;

    private final String category;

    private final Long price;

    private final Integer quantity;

    public static OrderItemResponse from(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getProduct().getProductName(), orderItem.getCategory(), orderItem.getPrice(), orderItem.getQuantity());
    }
}

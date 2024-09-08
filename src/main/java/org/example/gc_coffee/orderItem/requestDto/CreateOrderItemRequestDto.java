package org.example.gc_coffee.orderItem.requestDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.orderItem.entity.OrderItem;
import org.example.gc_coffee.product.entity.Product;


import java.util.UUID;


@Getter
public class CreateOrderItemRequestDto {


    @NotNull(message = "수량 정보를 확인해주세요.")
    private Integer quantity;

    @NotNull(message = "상품 ID가 올바르지 않습니다.")
    private UUID productId;

    public OrderItem of(Product product, Order order) {
        return OrderItem.builder()
                .order(order)
                .price(product.getPrice() * quantity)
                .quantity(quantity)
                .product(product)
                .build();
    }

}

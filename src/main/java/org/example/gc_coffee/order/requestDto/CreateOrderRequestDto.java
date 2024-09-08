package org.example.gc_coffee.order.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.example.gc_coffee.order.entity.Order;
import org.example.gc_coffee.orderItem.requestDto.CreateOrderItemRequestDto;
import org.example.gc_coffee.product.entity.Product;

import java.util.List;

@Getter
public class CreateOrderRequestDto {


    @NotEmpty(message = "이메일을 입력해주세요.")
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$",
            message="이메일 주소를 확인해주세요")
    private String email;

    @NotEmpty(message = "주소 정보를 입력해주세요.")
    private String address;

    @NotEmpty(message = "우편번호 정보를 입력해주세요.")
    private String postcode;

    @NotEmpty(message = "주문 항목이 비어 있습니다.")
    @Valid
    private List<CreateOrderItemRequestDto> items;


    public Order toEntity(){
        return Order.builder()
                .email(email)
                .address(address)
                .postcode(postcode)
                .build();
    }

}

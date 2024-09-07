package org.example.gc_coffee.product.requestDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateProductRequestDto {

    private String productName;

    private String category;

    private Long price;

    private String description;

}

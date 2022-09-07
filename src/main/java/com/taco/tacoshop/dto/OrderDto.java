package com.taco.tacoshop.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderDto {

    @NotNull(message = "상품 이름은 필수로 입력해주세요!")
    private Long itemId;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다!")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다!")
    private int count;

    @Builder
    public OrderDto(Long itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }
}

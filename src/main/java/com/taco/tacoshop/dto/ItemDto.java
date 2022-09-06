package com.taco.tacoshop.dto;


import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class ItemDto {
    private Long id;

    @NotBlank(message = "상품명을 필수 입력입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;

    @NotBlank(message = "상세 내용은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemStatus itemStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgId = new ArrayList<>();

    @Builder
    public ItemDto(String itemName, int price, String itemDetail, Integer stockNumber, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemStatus = itemStatus;
    }

    public Item toEntity(ItemDto itemDto){
        Item entity = Item.builder()
                .itemName(itemDto.itemName)
                .itemDetail(itemDto.itemDetail)
                .itemStatus(itemDto.itemStatus)
                .price(itemDto.price)
                .stockNumber(itemDto.stockNumber)
                .build();

        return entity;
    }

    public static ItemDto of(Item entity){
        ItemDto itemDto = ItemDto.builder()
                .itemName(entity.getItemName())
                .itemDetail(entity.getItemDetail())
                .itemStatus(entity.getItemStatus())
                .price(entity.getPrice())
                .stockNumber(entity.getStockNumber())
                .build();

        return itemDto;
    }
}

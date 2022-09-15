package com.taco.tacoshop.dto;


import lombok.Data;

@Data
public class OrderItemDto {

    private String itemName;

    private int count;

    private int orderPrice;

    private String imgUrl;


    public OrderItemDto(String itemName, int count, int orderPrice, String imgUrl) {
        this.itemName = itemName;
        this.count = count;
        this.orderPrice = orderPrice;
        this.imgUrl = imgUrl;
    }
}

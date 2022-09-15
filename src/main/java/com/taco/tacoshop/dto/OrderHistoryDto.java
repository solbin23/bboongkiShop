package com.taco.tacoshop.dto;

import com.taco.tacoshop.orders.Order;
import com.taco.tacoshop.orders.OrderStatus;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderHistoryDto {

    private Long orderId;

    private String orderDate;

    private OrderStatus orderStatus;

    //주문 상품리스트
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public OrderHistoryDto(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}

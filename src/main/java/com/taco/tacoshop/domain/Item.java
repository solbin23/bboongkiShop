package com.taco.tacoshop.domain;


import com.taco.tacoshop.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Column(nullable = false)
    private int stockNumber;

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Builder
    public Item(String itemName, String itemDetail, int stockNumber, int price, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.price = price;
        this.itemStatus = itemStatus;
    }
    public void updateItem(ItemDto itemDto){
        this.itemName = itemDto.getItemName();
        this.price = itemDto.getPrice();
        this.stockNumber = itemDto.getStockNumber();
        this.itemDetail = itemDto.getItemDetail();
        this.itemStatus = itemDto.getItemStatus();
    }
}

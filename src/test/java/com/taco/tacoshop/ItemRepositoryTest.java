package com.taco.tacoshop;

import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemStatus;
import com.taco.tacoshop.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void createItemTest(){
        Item item = Item.builder()
                .itemName("테스트 상품")
                .price(10000)
                .itemDetail("상품이지롱")
                .itemStatus(ItemStatus.SELL)
                .stockNumber(50)
                .build();
        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem.toString());
    }
}

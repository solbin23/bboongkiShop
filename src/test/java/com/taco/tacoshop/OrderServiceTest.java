package com.taco.tacoshop;

import com.taco.tacoshop.Member.Member;
import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemStatus;
import com.taco.tacoshop.dto.OrderDto;
import com.taco.tacoshop.orders.Order;
import com.taco.tacoshop.orders.OrderItem;
import com.taco.tacoshop.repository.ItemRepository;
import com.taco.tacoshop.repository.MemberRepository;
import com.taco.tacoshop.repository.OrderRepository;
import com.taco.tacoshop.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem(){
        Item item = Item.builder()
                .itemName("테스트상품")
                .price(10000000)
                .itemDetail("상품상세")
                .itemStatus(ItemStatus.SELL)
                .stockNumber(100)
                .build();
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = Member.builder()
                .email("test@email.com")
                .build();
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("주문테스트")
    public void order() {
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = OrderDto.builder()
                .itemId(item.getId())
                .count(10)
                .build();

        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        Assertions.assertEquals(totalPrice, order.getTotalPrice());

    }
}

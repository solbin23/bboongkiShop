package com.taco.tacoshop.service;

import com.taco.tacoshop.Member.Member;
import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.dto.OrderDto;
import com.taco.tacoshop.orders.Order;
import com.taco.tacoshop.orders.OrderItem;
import com.taco.tacoshop.repository.ItemRepository;
import com.taco.tacoshop.repository.MemberRepository;
import com.taco.tacoshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email){
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }
}

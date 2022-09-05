package com.taco.tacoshop.repository;

import com.taco.tacoshop.tacos.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

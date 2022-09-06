package com.taco.tacoshop.repository;

import com.taco.tacoshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

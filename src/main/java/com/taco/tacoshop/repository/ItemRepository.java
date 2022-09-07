package com.taco.tacoshop.repository;

import com.taco.tacoshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long> , QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
}

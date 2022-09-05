package com.taco.tacoshop.repository;

import com.taco.tacoshop.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

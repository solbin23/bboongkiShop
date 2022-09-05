package com.taco.tacoshop.repository;

import com.taco.tacoshop.tacos.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findByTitle(String title);
}

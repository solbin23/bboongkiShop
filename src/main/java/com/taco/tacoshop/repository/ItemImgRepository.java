package com.taco.tacoshop.repository;

import com.taco.tacoshop.domain.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
   List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}

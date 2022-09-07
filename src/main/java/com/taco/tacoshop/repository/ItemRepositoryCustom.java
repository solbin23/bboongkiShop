package com.taco.tacoshop.repository;

import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.dto.ItemSearchDto;
import com.taco.tacoshop.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}

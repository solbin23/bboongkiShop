package com.taco.tacoshop.dto;

import com.taco.tacoshop.domain.ItemStatus;
import lombok.Data;

@Data
public class ItemSearchDto {

    private String searchDateType;
    private ItemStatus searchStatus;
    private String searchBy;
    private String searchQuery="";
}

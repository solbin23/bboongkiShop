package com.taco.tacoshop.dto;

import com.taco.tacoshop.domain.ItemImg;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @Builder
    public ItemImgDto(String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity(ItemImgDto dto){
        ItemImg entity = ItemImg.builder()
                .imgName(dto.imgName)
                .oriImgName(dto.oriImgName)
                .imgUrl(dto.imgUrl)
                .repimgYn(dto.repImgYn)
                .build();

        return entity;
    }

    public static ItemImgDto of(ItemImg entity){
        ItemImgDto itemImgDto = ItemImgDto.builder()
                .imgName(entity.getImgName())
                .oriImgName(entity.getOriImgName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepimgYn())
                .build();

        return itemImgDto;
    }
}

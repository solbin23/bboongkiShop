package com.taco.tacoshop.service;

import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemImg;
import com.taco.tacoshop.dto.ItemDto;
import com.taco.tacoshop.dto.ItemImgDto;
import com.taco.tacoshop.repository.ItemImgRepository;
import com.taco.tacoshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.ExceptionTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public Long saveItem(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품등록
        Item item = itemDto.toEntity(itemDto);
        itemRepository.save(item);

        //이미지 등록
        for(int i = 0, max = itemImgFileList.size(); i < max; i++){
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .repimgYn(i == 0 ? "Y" : "N")
                    .build();

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemDto getItemDetail(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for (ItemImg itemImg : itemImgList){
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemDto itemDto = ItemDto.of(item);
        itemDto.setItemImgDtoList(itemImgDtoList);

        return itemDto;
    }

}

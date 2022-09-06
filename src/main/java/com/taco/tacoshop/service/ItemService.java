package com.taco.tacoshop.service;

import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemImg;
import com.taco.tacoshop.dto.ItemDto;
import com.taco.tacoshop.dto.ItemImgDto;
import com.taco.tacoshop.repository.ItemImgRepository;
import com.taco.tacoshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Long updateItem(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 수정
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemDto); //상품 등록 화면으로부터 전달받은 itemdto를 통해 상품 엔티티 업데이트

        List<Long> itemImgId = itemDto.getItemImgId();

        //이미지 등록
        for (int i = 0, max = itemImgFileList.size(); i < max; i++){
            itemImgService.updateItemImg(itemImgId.get(i),itemImgFileList.get(i));
        }

        return item.getId();
    }
}

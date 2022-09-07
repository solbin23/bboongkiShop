package com.taco.tacoshop;


import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.domain.ItemImg;
import com.taco.tacoshop.domain.ItemStatus;
import com.taco.tacoshop.dto.ItemDto;
import com.taco.tacoshop.repository.ItemImgRepository;
import com.taco.tacoshop.repository.ItemRepository;
import com.taco.tacoshop.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for (int i = 0; i <5; i++){
            String path = "/Users/solbin/project/upload";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFiles.add(multipartFile);
        }
        return multipartFiles;
    }

    @Test
    @DisplayName("상품등록테스트")
    public void saveItem() throws  Exception {
        ItemDto itemDto = ItemDto.builder()
                .itemName("테스트입니다")
                .itemStatus(ItemStatus.SELL)
                .itemDetail("설명입니다")
                .price(1000)
                .stockNumber(10)
                .build();

        List<MultipartFile> multipartFiles = createMultipartFiles();
        Long itemId = itemService.saveItem(itemDto, multipartFiles);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        Assertions.assertEquals(itemDto.getItemName(), item.getItemName());
        Assertions.assertEquals(multipartFiles.get(0).getOriginalFilename(),itemImgList.get(0).getOriImgName());
    }
}

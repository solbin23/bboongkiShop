package com.taco.tacoshop.controller;

import com.taco.tacoshop.domain.Item;
import com.taco.tacoshop.dto.ItemDto;
import com.taco.tacoshop.dto.ItemSearchDto;
import com.taco.tacoshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemDto",new ItemDto());
        return "/item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemDto itemDto, BindingResult bindingResult, Model model,
                          @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList)  {

        if (bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다!");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemDto, itemImgFileList);
        } catch (Exception e) {
            System.out.println("e = " + e);
            System.out.println("itemDto = " + itemDto);
            System.out.println("이미지파일 = " + itemImgFileList);
            model.addAttribute("errorMessage","상품 등록 중 오류가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping("/admin/item/{itemId}")
    public String adminItemDetail(@PathVariable("itemId") Long itemId, Model model){

        try {
            ItemDto itemDto = itemService.getItemDetail(itemId);
            model.addAttribute("itemDto", itemDto);
        }catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemDto",new ItemDto());
            return "item/itemForm";
        }
        return "item/itemForm";
    }

    @GetMapping(value = "/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model){

        ItemDto itemDto = itemService.getItemDetail(itemId);
        model.addAttribute("item", itemDto);

        return "item/itemDetail";
    }

    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemDto itemDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList, Model model){
        if (bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if (itemImgFileList.get(0).isEmpty() && itemDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력입니다.");
            return "item/itemForm";
        }
        try {
            itemService.saveItem(itemDto, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 오류가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = {"/admin/items","/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        model.addAttribute("items",items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/itemManage";
    }




}

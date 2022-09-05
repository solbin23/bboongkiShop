package com.taco.tacoshop.controller;

import com.taco.tacoshop.tacos.Ingredient;
import com.taco.tacoshop.tacos.Taco;
import com.taco.tacoshop.tacos.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {


    @GetMapping
    public String showDesignForm(Model model,Ingredient ingredient){

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","밀 또띠아", Type.WRAP),
                new Ingredient("COTO","콘 또띠아", Type.WRAP),
                new Ingredient("GRBF","소고기", Type.PROTEIN),
                new Ingredient("CARN", "돼지고기", Type.PROTEIN),
                new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC","Lettuce",Type.VEGGIES),
                new Ingredient("CHED","Cheddar",Type.CHEESE),
                new Ingredient("JACK","Montterey Jack", Type.CHEESE),
                new Ingredient("SLSA","Salsa", Type.SAUCE),
                new Ingredient("SRCR","Sour Cream", Type.SAUCE)

        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "Taco";
    }

    private List<Ingredient> filterByType(
        List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        if (errors.hasErrors()){
            return "Taco";
        }

        log.info("타코 디자인" + design);
        return "redirect:/orders/current";

    }

}

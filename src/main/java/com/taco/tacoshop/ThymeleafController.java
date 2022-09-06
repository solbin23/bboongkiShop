package com.taco.tacoshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/thymeleaf")
@Controller
public class ThymeleafController {
    @GetMapping("/ex")
    public String thymeleafController(){
        return "thymeleafEx";
    }

}

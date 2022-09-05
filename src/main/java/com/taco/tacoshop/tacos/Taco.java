package com.taco.tacoshop.tacos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "최소 5글자를 입력해 주세요!")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "하나는 선택해야 합니다!")
    private List<String> ingredients;

    private Date createDate;


    @PrePersist
    void createDate(){
        this.createDate = new Date();
    }


}

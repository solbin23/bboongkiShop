package com.taco.tacoshop.tacos;


import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String deliveryName;

    @NotBlank(message = "번지를 입력해주세요.")
    private String deliveryStreet;

    @NotBlank(message = "도시를 입력해주세요.")
    private String deliveryCity;

    @NotBlank(message = "국가를 입력해주세요.")
    private String deliveryState;

    @NotBlank(message = "우편번호를 입력해주세요.")
    private String deliveryZip;

    @CreditCardNumber(message = "카드가 유효하지 않습니다.")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "MM/YY 로 입력해주세요.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "유효하지 않은 CVV 번호 입니다.")
    private String ccCVV;

    private Date placeDate;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design){
        this.tacos.add(design);
    }
    @PrePersist
    void placeDate(){
        this.placeDate = new Date();
    }
}
*/

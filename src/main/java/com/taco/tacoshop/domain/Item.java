package com.taco.tacoshop.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "item")
public class Item extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private String content;
}

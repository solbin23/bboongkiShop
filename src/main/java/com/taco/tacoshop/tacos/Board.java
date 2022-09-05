package com.taco.tacoshop.tacos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @Column(length = 50)
    @NotEmpty
    private String nickname;

    @Column(length = 200)
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @CreatedDate
    private LocalDateTime time;

    @PrePersist
    public void time(){
        this.time = LocalDateTime.now();
    }
}

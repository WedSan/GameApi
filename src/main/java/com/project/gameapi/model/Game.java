package com.project.gameapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public Game(ReqGame reqGame){
        this.name = reqGame.name();
        this.description = reqGame.description();
        this.price = reqGame.price();
    }

    public void update(ReqUpdateGame reqGame){
        if(reqGame.name()!=null){
            this.name = reqGame.name();
        }
        if(reqGame.description()!=null){
            this.description = reqGame.description();
        }
        if(reqGame.price()!=null){
            this.price = reqGame.price();
        }
    }
}

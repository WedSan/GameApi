package com.project.gameapi.model;

import java.math.BigDecimal;

public record  ReqUpdateGame(
        String name,
        String description,
        BigDecimal price
){
}

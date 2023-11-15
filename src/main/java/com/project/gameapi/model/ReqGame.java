package com.project.gameapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ReqGame(
        @NotBlank
        String name,
        String description,
        @NotNull
        BigDecimal price

) {

}

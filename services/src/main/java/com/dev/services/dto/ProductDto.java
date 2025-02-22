package com.dev.services.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;
    @NotNull
    private double qtStock;
}

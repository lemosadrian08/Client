package com.example.clientservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDto {
    private Long productoId ;
    private Integer codigo ;
    private String descripcion ;
    private Integer cantidad ;
    private BigDecimal precio ;
}

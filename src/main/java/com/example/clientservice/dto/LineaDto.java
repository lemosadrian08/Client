package com.example.clientservice.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineaDto {

    private Integer lineaId;
    private Integer cantidad;
    private String descripcion;
    private BigDecimal precio;
    private ProductoDto producto;
    private Integer facturaId;
}

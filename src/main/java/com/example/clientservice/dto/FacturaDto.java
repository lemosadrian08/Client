package com.example.clientservice.dto;


import com.example.clientservice.model.ClienteModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class FacturaDto {

    private Integer facturaId;
    private Integer cantidad;
    private Date fecha;
    private BigDecimal total;
    private ClienteModel cliente;
    private Set<LineaDto> lineas;

}

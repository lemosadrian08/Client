package com.example.clientservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="productos")
public class ProductoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId ;
    private Integer codigo ;
    private String descripcion ;
    private Integer cantidad ;
    private BigDecimal precio ;
}

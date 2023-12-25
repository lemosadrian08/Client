package com.example.clientservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="clientes")
public class ClienteModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;
    private String nombre;
    private String apellido;
    private Integer dni;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

}

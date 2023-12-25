package com.example.clientservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name="Lineas")
public class LineaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linea_id")
    private Integer lineaId;

    private Integer cantidad;

    private String descripcion;

    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name="factura_id")
    private FacturaModel factura;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private ProductoModel producto;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Linea [");

        appendField(builder, "lineaid", lineaId);
        appendField(builder, "cantidad", cantidad);
        appendField(builder, "descripcion", descripcion);
        appendField(builder, "precio", precio);

        builder.append("]");

        return builder.toString();
    }

    //El metodo se encarga de agregar el nombre del campo y su valor al StringBuilder solo si el valor no es nulo.
    private void appendField(StringBuilder builder, String fieldName, Object fieldValue) {
        if (fieldValue != null) {
            builder.append(fieldName).append("=").append(fieldValue);
        }
        builder.append(", ");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lineaId == null) ? 0 : lineaId.hashCode());
        return result;
    }

}

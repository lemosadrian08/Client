package com.example.clientservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="facturas")
@NamedQuery(name="FacturaModel.findAll", query="SELECT f FROM FacturaModel f")
public class FacturaModel {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Integer facturaId;
    private Integer cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private ClienteModel cliente;

    private BigDecimal total;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private Set<LineaModel> lineas = new HashSet<>();


    //Patron de gestión de relaciones bidireccionales

    public void addLinea(LineaModel linea) {
        if (linea != null && !this.lineas.contains(linea)) {
            this.lineas.add(linea);
            linea.setFactura(this);
        }
    }

    public LineaModel removeLinea(LineaModel linea) {
        getLineas().remove(linea);
        linea.setFactura(null);
        return linea;
    }

    //StringBuilder
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Factura [");

        appendField(builder, "facturaid", facturaId);
        appendField(builder, "cantidad", cantidad);
        appendField(builder, "fecha", fecha);
        appendField(builder, "total", total);
        appendField(builder, "cliente", cliente);
        appendField(builder, "lineas", lineas);

        builder.append("]");

        return builder.toString();
    }

    //Metodo auxiliar para agregar el nombre y el valor del campo solo si el valor no es nulo.
    private void appendField(StringBuilder builder, String fieldName, Object fieldValue) {
        if (fieldValue != null) {
            builder.append(fieldName).append("=").append(fieldValue).append(", ");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((facturaId == null) ? 0 : facturaId.hashCode());
        return result;
    }

}



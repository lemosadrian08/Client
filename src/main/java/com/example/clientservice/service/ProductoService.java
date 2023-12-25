package com.example.clientservice.service;


import com.example.clientservice.dto.ProductoDto;
import com.example.clientservice.model.ProductoModel;
import com.example.clientservice.reository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    public ProductoModel create(ProductoModel productoModel){

        //aca validamos que los datos del producto esten completos
        //ademas validamos que la el codigo tenga el formato correcto

        if(productoModel.getDescripcion().isBlank()){
            return new ProductoModel();
        }
        return this.productoRepository.save(productoModel);

    }

    public ProductoDto findById(Long id){
        Optional<ProductoModel> caja = this.productoRepository.findById(id);

        if (caja.isPresent()){
            return this.toDTO(caja.get());
        }

        return new ProductoDto();
    }

    private ProductoDto toDTO(ProductoModel producto){
        ProductoDto dto = new ProductoDto();
        dto.setProductoId(producto.getProductoId());
        dto.setCodigo(producto.getCodigo());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCantidad(producto.getCantidad());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }
}

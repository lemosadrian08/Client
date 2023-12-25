package com.example.clientservice.controller;


import com.example.clientservice.dto.ProductoDto;
import com.example.clientservice.model.ProductoModel;
import com.example.clientservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path ="api/productos")
@RestController

public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping("/")
    public ProductoModel create (@RequestBody ProductoModel productoModel){
        return this.productoService.create(productoModel);
    }


    @GetMapping("/{id}")
    public ProductoDto findById(@PathVariable Long id){
        return this.productoService.findById(id);
    }
}


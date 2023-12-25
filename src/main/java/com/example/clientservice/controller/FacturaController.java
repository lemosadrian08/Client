package com.example.clientservice.controller;


import com.example.clientservice.dto.ClienteDto;
import com.example.clientservice.dto.FacturaDto;
import com.example.clientservice.dto.ProductoDto;
import com.example.clientservice.model.ClienteModel;
import com.example.clientservice.model.FacturaModel;
import com.example.clientservice.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/")
    public FacturaDto create (@RequestBody FacturaDto facturaDto){
        return this.facturaService.create(facturaDto);
    }

    @GetMapping("/{id}")
    public FacturaDto findById(@PathVariable Integer id){
        return this.facturaService.getFacturaById(id);
    }


}


package com.example.clientservice.controller;

import com.example.clientservice.dto.ClienteDto;
import com.example.clientservice.model.ClienteModel;
import com.example.clientservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path ="api/clientes")
@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/")
    public ClienteModel create (@RequestBody ClienteModel clienteModel){
        return this.clienteService.create(clienteModel);
    }

    @GetMapping("/{id}")
    public ClienteDto findById(@PathVariable Long id){
        return this.clienteService.findById(id);
    }

}



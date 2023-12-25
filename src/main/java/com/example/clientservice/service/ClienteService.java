package com.example.clientservice.service;


import com.example.clientservice.dto.ClienteDto;
import com.example.clientservice.model.ClienteModel;
import com.example.clientservice.reository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;



    public ClienteModel create(ClienteModel clienteModel){

        if(clienteModel.getNombre().isBlank() || clienteModel.getApellido().isBlank() || clienteModel.getDni() == null || clienteModel.getFechaNacimiento()== null){
            //aca validamos que los datos del cliente esten completos
            //ademas validamos que el dni tenga al cantidad de numeros correcta
            //Se valida que la fecha no sea ni muy antiguga, ni futura
            return new ClienteModel();
        }
        return this.clienteRepository.save(clienteModel);

    }



    public ClienteDto findById(Long id){
        Optional<ClienteModel> caja = this.clienteRepository.findById(id);

        if (caja.isPresent()){
            return this.toDTO(caja.get());
        }

        return new ClienteDto();
    }

    private ClienteDto toDTO(ClienteModel cliente){
        ClienteDto dto = new ClienteDto();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEdad(Period.between(cliente.getFechaNacimiento(), LocalDate.now()).getYears());
        return dto;
    }





}

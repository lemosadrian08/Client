package com.example.clientservice;

import com.example.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ClientserviceApplication {


	//@Autowired
	//ClientService clientService;

	public static void main(String[] args) {

		SpringApplication.run(ClientserviceApplication.class, args);
	}

}

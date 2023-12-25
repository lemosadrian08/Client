package com.example.clientservice.reository;

import com.example.clientservice.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel,Long> {

}

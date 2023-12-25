package com.example.clientservice.reository;

import com.example.clientservice.model.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<FacturaModel,Integer> {
}

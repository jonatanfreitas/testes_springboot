package com.financeiroteste.repository;

import com.financeiroteste.model.Banco;
import org.springframework.data.jpa.repository.Query;

public interface BancoRepository <Banco, Long> {

    @Query(value= "SELECT MAX(e.id) FROM Bairro e")
    Long max();

}

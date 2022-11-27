package com.example.ObDDA2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.ObDDA2.entity.Cliente;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Modifying
    @Query(value = "DELETE FROM persona p WHERE p.id = :clienteId", nativeQuery = true)
    public void deletePersonaById(@Param("clienteId") Long clienteId);
}

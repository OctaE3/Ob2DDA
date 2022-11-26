package com.example.ObDDA2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.entity.Persona;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query(value = "SELECT p.* FROM persona p", nativeQuery = true)
    public Iterable<Persona> findAllPersonas();
}

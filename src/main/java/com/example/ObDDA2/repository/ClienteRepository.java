package com.example.ObDDA2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ObDDA2.entity.Cliente;
import com.example.ObDDA2.entity.Viaje;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query(value = "SELECT v.* FROM viajes v INNER JOIN clientes_viajes cv ON v.id = cv.viaje_id WHERE cv.cliente_ci = :clienteCi", nativeQuery = true)
    public List<Viaje> findViajesByClienteCi(@Param("clienteCi") Long clienteCi);
}

package com.example.ObDDA2.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ObDDA2.entity.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query(value = "SELECT v.* FROM viajes v WHERE v.eliminado = false", nativeQuery = true)
    public Iterable<Viaje> findAllFalse();

    @Query(value = "SELECT v.* FROM viajes v WHERE v.id NOT IN (SELECT v.id FROM viajes v INNER JOIN clientes_viajes cv ON v.id = cv.viaje_id WHERE v.eliminado = false AND cv.cliente_id = :clienteId)", nativeQuery = true)
    public Iterable<Viaje> findViajesNotInViajesCliente(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT v.* FROM viajes v INNER JOIN clientes_viajes cv ON v.id = cv.viaje_id WHERE v.eliminado = false AND cv.cliente_id = :clienteId", nativeQuery = true)
    public List<Viaje> findViajesByClienteId(@Param("clienteId") Long clienteId);

    @Modifying
    @Query(value = "DELETE FROM clientes_viajes WHERE cliente_id = :clienteId AND viaje_id = :viajeId", nativeQuery = true)
    public void deleteViajeClienteById(@Param("clienteId") Long clienteId, @Param("viajeId") Long viajeId);

    @Modifying
    @Query(value = "DELETE FROM clientes_viajes WHERE cliente_id = :clienteId", nativeQuery = true)
    public void deleteAllViajesClienteById(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT v.* FROM viajes v INNER JOIN clientes_viajes cv ON v.id = cv.viaje_id WHERE v.fecha > :fechaViaje AND cv.cliente_id = :clienteId ORDER BY v.fecha LIMIT 1", nativeQuery = true)
    public Viaje findViajeByClienteIdAndViajeFecha(@Param("fechaViaje") Date fechaViaje, @Param("clienteId") Long clienteId);
}

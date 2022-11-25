package com.example.ObDDA2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.example.ObDDA2.entity.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    @Query(value = "SELECT v.* FROM viajes v WHERE v.eliminado = false", nativeQuery = true)
    public Iterable<Viaje> findAllFalse();
}

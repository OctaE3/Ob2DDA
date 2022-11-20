package com.example.ObDDA2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.ObDDA2.entity.ClienteVip;

public interface ClienteVipRepository extends JpaRepository<ClienteVip, Long>{
    Optional<ClienteVip> findByCi(int Ci);

    void deleteByCi(int Ci);
}

package com.example.ObDDA2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ObDDA2.entity.ClienteVip;

@Repository
public interface ClienteVipRepository extends JpaRepository<ClienteVip, Long>{
    
}

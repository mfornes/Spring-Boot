package com.musala.gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.gateway.entity.Gateway;

@Repository
public interface GatewayRepository
        extends JpaRepository<Gateway, Long> {

    Optional<Gateway> findByGatewayName(String gatewayName);
}
package com.musala.gateway.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.musala.gateway.entity.Device;
import com.musala.gateway.entity.Gateway;

@Repository
public interface DeviceRepository
                extends JpaRepository<Device, Long> {

        @Lock(LockModeType.PESSIMISTIC_READ)
        @Query("SELECT COUNT(*) FROM Device WHERE gateway = :gateway")
        Integer cantDeviceByGatewayId(Gateway gateway);

        Optional<Device> findByUid(Number uid);

}
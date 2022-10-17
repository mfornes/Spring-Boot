package com.musala.gateway.service;

import java.util.List;

import com.musala.gateway.dto.GatewayDTO;

public interface GatewayService {

    GatewayDTO saveGateway(GatewayDTO gateway);

    List<GatewayDTO> fetchGatewayList();

    GatewayDTO gatewayById(Long id);

    GatewayDTO updateGateway(GatewayDTO gateway, Long id);

    void deleteGatewayById(Long id);

    void deleteAllGateway();
}
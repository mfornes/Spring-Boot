package com.musala.gateway.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.gateway.dto.GatewayDTO;
import com.musala.gateway.entity.Gateway;

@Component
public class GatewayConverter {

    @Autowired
    private ModelMapper modelMapper;

    public GatewayDTO convertEntityToDto(Gateway gateway) {
        GatewayDTO gatewayDTO = modelMapper.map(gateway, GatewayDTO.class);
        return gatewayDTO;
    }

    public Gateway convertDtoToEntity(GatewayDTO gatewayDTO) {
        Gateway gateway = modelMapper.map(gatewayDTO, Gateway.class);
        return gateway;
    }
}
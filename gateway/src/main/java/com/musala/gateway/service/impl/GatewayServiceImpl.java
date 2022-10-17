package com.musala.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musala.gateway.converter.GatewayConverter;
import com.musala.gateway.dto.GatewayDTO;
import com.musala.gateway.entity.Gateway;
import com.musala.gateway.errors.exception.NoResourceExistsException;
import com.musala.gateway.errors.exception.ResourceAlreadyExistsException;
import com.musala.gateway.repository.GatewayRepository;
import com.musala.gateway.service.GatewayService;

@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    GatewayConverter gatewayConverter;

    @Override
    @Transactional
    public GatewayDTO saveGateway(GatewayDTO gatewayDto) {

        Gateway gateway = gatewayConverter.convertDtoToEntity(gatewayDto);
        Gateway existingGateway = gatewayRepository.findByGatewayName(gateway.getGatewayName()).orElse(null);

        if (existingGateway != null)
            throw new ResourceAlreadyExistsException("Gateway already exists!!");

        gateway = gatewayRepository.save(gateway);

        return gatewayConverter.convertEntityToDto(gateway);
    }

    @Override
    public List<GatewayDTO> fetchGatewayList() {
        List<GatewayDTO> listGatewayDTO = new ArrayList<>();
        gatewayRepository.findAll().forEach(gateway -> {
            listGatewayDTO.add(gatewayConverter.convertEntityToDto(gateway));
        });
        return listGatewayDTO;
    }

    @Override
    public GatewayDTO gatewayById(Long id) {

        Gateway gateway = gatewayRepository.findById(id).orElseThrow(() -> new NoResourceExistsException(
                "NO Gateway PRESENT WITH ID = " + id));

        return gatewayConverter.convertEntityToDto(gateway);
    }

    @Override
    @Transactional
    public GatewayDTO updateGateway(GatewayDTO gateway, Long id) {

        Gateway existingGateway = gatewayRepository.findById(id).orElse(null);

        if (existingGateway == null)
            throw new NoResourceExistsException("No Gateway exists!!");

        existingGateway.setGatewayName(gateway.getGatewayName());

        existingGateway.setAddressIPv4(gateway.getAddressIPv4());

        existingGateway = gatewayRepository.save(existingGateway);

        return gatewayConverter.convertEntityToDto(existingGateway);

    }

    @Override
    public void deleteGatewayById(Long id) {

        Gateway existingGateway = gatewayRepository.findById(id).orElse(null);

        if (existingGateway == null)
            throw new NoResourceExistsException("No Gateway exists!!");

        gatewayRepository.deleteById(id);
    }

    @Override
    public void deleteAllGateway() {
        gatewayRepository.deleteAll();
    }

}
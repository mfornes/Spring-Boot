package com.musala.gateway.service.impl;

import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musala.gateway.converter.DeviceConverter;
import com.musala.gateway.converter.GatewayConverter;
import com.musala.gateway.dto.DeviceDTO;
import com.musala.gateway.entity.Device;
import com.musala.gateway.entity.Gateway;
import com.musala.gateway.enums.Status;
import com.musala.gateway.errors.exception.NoMoreDevicesAllowed;
import com.musala.gateway.errors.exception.NoResourceExistsException;
import com.musala.gateway.errors.exception.ResourceAlreadyExistsException;
import com.musala.gateway.repository.DeviceRepository;
import com.musala.gateway.repository.GatewayRepository;
import com.musala.gateway.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    DeviceConverter deviceConverter;

    @Autowired
    GatewayConverter gatewayConverter;

    @Override
    @Transactional
    public DeviceDTO saveDevice(DeviceDTO deviceDTO, Long gatewayId) {

        Gateway gateway = gatewayRepository.findById(gatewayId).orElseThrow(() -> new NoResourceExistsException(
                "No gateway present with id = " + gatewayId));

        Integer cant = deviceRepository.cantDeviceByGatewayId(gateway);

        if (cant > 9)
            throw new NoMoreDevicesAllowed("No more devices allowed.");

        Device device = deviceConverter.convertDtoToEntity(deviceDTO);
        Device existingDevice = deviceRepository.findByUid(device.getUid()).orElse(null);

        if (existingDevice != null)
            throw new ResourceAlreadyExistsException("Device already exists!!");

        device.setGateway(gateway);

        device = deviceRepository.save(device);

        return deviceConverter.convertEntityToDto(device);

    }

    @Override
    public List<DeviceDTO> fetchDeviceList(Long gatewayId) {

        if (!gatewayRepository.existsById(gatewayId))
            throw new NoResourceExistsException("Not found gateway with id = " + gatewayId);

        List<DeviceDTO> devices = new ArrayList<DeviceDTO>();

        deviceRepository.findAll().forEach(device -> {
            if (device.getGateway().getId().equals(gatewayId)) {
                devices.add(deviceConverter.convertEntityToDto(device));
            }

        });

        return devices;

    }

    @Override
    public DeviceDTO getDevicesByGatewayId(Long id) {

        Device device = deviceRepository.findById(id).orElseThrow(() -> new NoResourceExistsException(
                "NO Device PRESENT WITH ID = " + id));

        return deviceConverter.convertEntityToDto(device);
    }

    @Override
    @Transactional
    public DeviceDTO updateDevice(DeviceDTO device, Long gatewayId, Long id) {

        Device existingDevice = deviceRepository.findById(id).orElse(null);

        if (existingDevice == null)
            throw new NoResourceExistsException("No Device exists!!");

        Gateway gateway = gatewayRepository.findById(gatewayId).orElseThrow(() -> new NoResourceExistsException(
                "NO Gateway present with id = " + gatewayId));

        existingDevice.setUid(device.getUid());        
        existingDevice.setStatus(Status.valueOf(device.getStatus()));
        existingDevice.setVendor(device.getVendor());
        existingDevice.setGateway(gateway);

        existingDevice = deviceRepository.save(existingDevice);

        return deviceConverter.convertEntityToDto(existingDevice);
    }

    @Override
    public void deleteDeviceById(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public String deleteAllDevicesOfGateway(Long gatewayId) {
        if (!gatewayRepository.existsById(gatewayId)) {
            // throw new ResourceNotFoundException("Not found gateway with id = " +
            // gatewayId);
        }

        return null;
    }
}

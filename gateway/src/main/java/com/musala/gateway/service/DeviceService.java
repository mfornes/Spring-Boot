package com.musala.gateway.service;

import java.util.List;

import com.musala.gateway.dto.DeviceDTO;

public interface DeviceService {

    DeviceDTO saveDevice(DeviceDTO device, Long gatewayId);

    List<DeviceDTO> fetchDeviceList(Long gatewayId);

    DeviceDTO getDevicesByGatewayId(Long id);

    DeviceDTO updateDevice(DeviceDTO device, Long gatewayId, Long id);

    void deleteDeviceById(Long id);

    String deleteAllDevicesOfGateway(Long gatewayId);
}
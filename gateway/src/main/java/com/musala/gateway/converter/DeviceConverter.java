package com.musala.gateway.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.gateway.dto.DeviceDTO;
import com.musala.gateway.entity.Device;

@Component
public class DeviceConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DeviceDTO convertEntityToDto(Device device) {
        DeviceDTO deviceDTO = modelMapper.map(device, DeviceDTO.class);
        return deviceDTO;
    }

    public Device convertDtoToEntity(DeviceDTO deviceDTO) {
        Device device = modelMapper.map(deviceDTO, Device.class);
        return device;
    }
}
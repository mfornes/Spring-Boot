package com.musala.gateway.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.musala.gateway.annotation.IPv4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatewayDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String uuid;

    @NotNull
    @NotBlank
    private String gatewayName;

    @IPv4
    @NotNull
    @NotBlank
    private String addressIPv4;

    private List<DeviceDTO> devices;
}
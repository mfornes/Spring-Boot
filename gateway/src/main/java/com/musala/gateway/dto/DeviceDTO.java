package com.musala.gateway.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
public class DeviceDTO {

    private Long id;
  
    private String uid;

    @NotNull
    @NotBlank
    private String vendor;

    @NotNull
    @NotBlank
    private String status;

    private Date dateCreated;

    private Long gatewayId;
}
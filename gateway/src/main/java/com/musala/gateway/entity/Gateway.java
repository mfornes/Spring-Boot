package com.musala.gateway.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.gateway.annotation.IPv4;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;
    
    @PrePersist
    private void generateUuid(){ uuid = UUID.randomUUID().toString();}

    @NotNull
    private String gatewayName;

    @IPv4
    @NotNull
    private String addressIPv4;

    @OneToMany(mappedBy = "gateway")
    @JsonIgnore
    private List<Device> devices;
}
package com.musala.gateway;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.musala.gateway.converter.GatewayConverter;
import com.musala.gateway.dto.GatewayDTO;
import com.musala.gateway.entity.Gateway;
import com.musala.gateway.errors.exception.NoResourceExistsException;
import com.musala.gateway.errors.exception.ResourceAlreadyExistsException;
import com.musala.gateway.repository.GatewayRepository;
import com.musala.gateway.service.impl.GatewayServiceImpl;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(GatewayServiceTests.class)
public class GatewayServiceTests {

    @Mock
    private GatewayRepository repository;

    @Mock
    ResourceAlreadyExistsException exception;

    @Mock
    GatewayConverter converter;

    @InjectMocks
    private GatewayServiceImpl service;

    private GatewayDTO gatewayDto;

    private Gateway gateway;

    private GatewayDTO gatewayDtoConverter;

    private Gateway gatewayConverter;

    private List<Gateway> listGateway;

    private List<GatewayDTO> listGatewayDTO;

    @BeforeEach
    public void setup() {
        gatewayDto = GatewayDTO.builder()
                .id(1L)
                .gatewayName("Gateway 1")
                .addressIPv4("10.9.0.3")
                .uuid("64172f7b-eeea-41b5-ac78-180eba261083")
                .devices(null)
                .build();

        gateway = Gateway.builder()
                .id(1L)
                .gatewayName("Gateway 1")
                .addressIPv4("10.9.0.3")
                .uuid("64172f7b-eeea-41b5-ac78-180eba261083")
                .devices(null)
                .build();

        gatewayDtoConverter = converter.convertEntityToDto(gateway);
        gatewayConverter = converter.convertDtoToEntity(gatewayDto);

        listGateway = List.of(gateway, gateway, gateway, gateway, gateway);
        listGatewayDTO = List.of(gatewayDto, gatewayDto, gatewayDto, gatewayDto, gatewayDto);
    }

    @Test
    public void test_save_gateway_already_exists() {

        when(gatewayConverter).thenReturn(gateway);
        when(repository.findByGatewayName(gateway.getGatewayName())).thenReturn(Optional.ofNullable(gateway));
       
        assertThatThrownBy(() -> { service.saveGateway(gatewayDto); })
        .isInstanceOf(ResourceAlreadyExistsException.class)
        .hasMessage("Gateway already exists!!");
        
    }

    @Test
    public void test_save_gateway_no_exists() {

        when(converter.convertDtoToEntity(gatewayDto)).thenReturn(gateway);
        when(repository.findByGatewayName(gateway.getGatewayName())).thenReturn(Optional.empty());
        when(repository.save(gateway)).thenReturn(gateway);
        
        assertThat(service.saveGateway(gatewayDto)).isEqualTo(gatewayDtoConverter);
        
    }

    @Test
    void test_fetch_all_and_return_list_gateway() {
 
        when(repository.findAll()).thenReturn(listGateway);
        when(converter.convertEntityToDto(gateway)).thenReturn(gatewayDto);

        assertThat(service.fetchGatewayList()).isEqualTo(listGatewayDTO);
    }

    @Test
    void test_fetch_all_and_return_empty_list_gateway() {
 
        when(repository.findAll()).thenReturn(Collections.<Gateway>emptyList());

        assertThat(service.fetchGatewayList()).isEqualTo(Collections.<GatewayDTO>emptyList());
    }

    @Test
    void test_not_found_a_gateway_that_doesnt_exists() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> { service.gatewayById(1L); })
        .isInstanceOf(NoResourceExistsException.class)
        .hasMessage("NO Gateway PRESENT WITH ID = " + 1L);

    }

    @Test
    void test_found_a_gateway_that_exists() {

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(gateway));
        when(converter.convertEntityToDto(gateway)).thenReturn(gatewayDto);

        assertThat(service.gatewayById(anyLong())).isEqualTo(gatewayDto);

    }

    @Test
    void test_update_and_return_gateway() {     

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(gateway));
        when(repository.save(gateway)).thenReturn(gateway);       
        when(converter.convertEntityToDto(gateway)).thenReturn(gatewayDto);

        assertThat(service.updateGateway(gatewayDto, anyLong())).isEqualTo(gatewayDto);

    }

    @Test
    void test_update_and_return_doesnt_exists_gateway() {     

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> { service.updateGateway(gatewayDto, anyLong()); })
        .isInstanceOf(NoResourceExistsException.class)
        .hasMessage("No Gateway exists!!");
    }

    @Test
    void test_delete_one_gateway_and_return_doesnt_exists() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> { service.deleteGatewayById(anyLong()); })
        .isInstanceOf(NoResourceExistsException.class)
        .hasMessage("No Gateway exists!!");
    }

    @Test
    void test_delete_one_gateway() {

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(gateway));

        doNothing().when(repository).deleteById(1L);

        service.deleteGatewayById(1L);

        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }
}
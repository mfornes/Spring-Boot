package com.musala.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gateway.dto.DeviceDTO;
import com.musala.gateway.service.DeviceService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@PostMapping("/gateway/{gatewayId}/device")
	public ResponseEntity<DeviceDTO> saveDevice(@PathVariable(value = "gatewayId") Long gatewayId,
			@RequestBody DeviceDTO device) {
		DeviceDTO saveDevice = deviceService.saveDevice(device, gatewayId);
		return new ResponseEntity<>(saveDevice, HttpStatus.CREATED);
	}

	@GetMapping("/gateway/{gatewayId}/device")
	public ResponseEntity<List<DeviceDTO>> getAllDevicesByGatewayId(@PathVariable(value = "gatewayId") Long gatewayId) {

		List<DeviceDTO> deviceList = deviceService.fetchDeviceList(gatewayId);
		if (deviceList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(deviceList, HttpStatus.OK);
	}

	@GetMapping("/device/{id}")
	public ResponseEntity<DeviceDTO> getDevicesByGatewayId(@PathVariable(value = "id") Long id) {

		DeviceDTO device = deviceService.getDevicesByGatewayId(id);
		if (device == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(device, HttpStatus.OK);
	}

	@PutMapping("/{gatewayId}/device/{id}")
	public ResponseEntity<DeviceDTO> updateDevice(@RequestBody DeviceDTO device,  @PathVariable("gatewayId") Long gatewayId, @PathVariable("id") Long id) {

		DeviceDTO updateDevice = deviceService.updateDevice(device, gatewayId, id);
		if (updateDevice == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(updateDevice, HttpStatus.OK);
	}

	@DeleteMapping("/device/{id}")
	public ResponseEntity<String> deleteDeviceById(@PathVariable("id") Long id) {
		deviceService.deleteDeviceById(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/gateway/{gatewayId}/device")
	public ResponseEntity<String> deleteAllDevicesOfGateway(@PathVariable("gatewayId") Long gatewayId) {
		// deviceService.deleteDeviceById(gatewayId);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
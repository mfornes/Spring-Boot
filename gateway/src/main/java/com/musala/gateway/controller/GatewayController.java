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

import com.musala.gateway.dto.GatewayDTO;
import com.musala.gateway.service.GatewayService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GatewayController {

	@Autowired
	private GatewayService gatewayService;

	@PostMapping("/gateway")
	public ResponseEntity<GatewayDTO> saveGateway(@Valid @RequestBody GatewayDTO gateway) {

		GatewayDTO saveGateway = gatewayService.saveGateway(gateway);
		return new ResponseEntity<>(saveGateway, HttpStatus.CREATED);
	}

	@GetMapping("/gateway")
	public ResponseEntity<List<GatewayDTO>> fetchGatewayList() {

		List<GatewayDTO> gatewayList = gatewayService.fetchGatewayList();

		if (gatewayList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(gatewayList, HttpStatus.OK);
	}

	@GetMapping("/gateway/{id}")
	public ResponseEntity<GatewayDTO> fetchGatewayById(@PathVariable("id") Long id) {

		GatewayDTO gateway = gatewayService.gatewayById(id);
		if (gateway == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(gateway, HttpStatus.OK);
	}

	@PutMapping("/gateway/{id}")
	public ResponseEntity<GatewayDTO> updateGateway(@Valid @RequestBody GatewayDTO gateway, @PathVariable("id") Long id) {

		GatewayDTO updateGateway = gatewayService.updateGateway(gateway, id);
		if (updateGateway == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(gateway, HttpStatus.OK);
	}

	@DeleteMapping("/gateway/{id}")
	public ResponseEntity<String> deleteGatewayById(@PathVariable("id") Long id) {
		gatewayService.deleteGatewayById(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/gateway")
	public ResponseEntity<String> deleteAllGateway() {
		gatewayService.deleteAllGateway();
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
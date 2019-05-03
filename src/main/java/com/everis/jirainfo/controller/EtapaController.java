package com.everis.jirainfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.jirainfo.dto.EtapaDTO;
import com.everis.jirainfo.service.EtapaService;

@RestController
@RequestMapping("/api/etapa")
public class EtapaController {

	@Autowired
	private EtapaService etapaService;
	
	@GetMapping
	public ResponseEntity<List<EtapaDTO>> listar() {
		List<EtapaDTO> etapasDTO = etapaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(etapasDTO);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<EtapaDTO> buscar(@PathVariable("id") Long id) {
		EtapaDTO etapaDTO = etapaService.obterUm(id);
		return ResponseEntity.status(HttpStatus.OK).body(etapaDTO);		
	}
}
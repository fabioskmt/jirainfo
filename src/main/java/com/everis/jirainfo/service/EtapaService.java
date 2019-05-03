package com.everis.jirainfo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.jirainfo.dto.EtapaDTO;
import com.everis.jirainfo.entity.Etapa;
import com.everis.jirainfo.repository.EtapaRepository;
import com.everis.jirainfo.util.ConversorDTO;


@Service
public class EtapaService {

	@Autowired
	private EtapaRepository etapaRepository;

	public List<EtapaDTO> listar() {
		List<Etapa> etapas = etapaRepository.findAll();
		
		List<EtapaDTO> etapasDTO = new ArrayList<EtapaDTO>();
		for (Etapa etapa : etapas) {
			etapasDTO.add(ConversorDTO.converterEtapaParaDTO(etapa));
		}
		
		return etapasDTO;
	}
	
	public EtapaDTO obterUm(Long id) {
		return ConversorDTO.converterEtapaParaDTO(etapaRepository.findById(id).get());
	}
}

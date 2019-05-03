package com.everis.jirainfo.util;

import java.util.ArrayList;
import java.util.List;

import com.everis.jirainfo.dto.EtapaDTO;
import com.everis.jirainfo.dto.ItemDTO;
import com.everis.jirainfo.entity.Etapa;
import com.everis.jirainfo.entity.Item;

public class ConversorDTO {
	
	public static EtapaDTO converterEtapaParaDTO(Etapa etapa) {
		EtapaDTO etapaDTO = new EtapaDTO();
		etapaDTO.setId(etapa.getId());
		etapaDTO.setStatusInicial(etapa.getStatusInicial());
		etapaDTO.setDataStatusInicial(etapa.getDataStatusInicial());
		etapaDTO.setTempoStatusInicial(etapa.getTempoStatusInicial());
		etapaDTO.setStatusFinal(etapa.getStatusFinal());
		etapaDTO.setTempoStatusFinal(etapa.getTempoStatusFinal());
		etapaDTO.setChaveItem(etapa.getItem().getChave());
		
		return etapaDTO;
	}
	
	
	public static ItemDTO converterItemParaDTO(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		
		itemDTO.setChave(item.getChave());
		itemDTO.setResumo(item.getResumo());
		itemDTO.setResponsavel(item.getResponsavel());
		itemDTO.setPontos(item.getPontos());
		itemDTO.setSituacao(item.getSituacao());
		itemDTO.setTipo(item.getTipo());
		
		List<Etapa> etapas = item.getListaEtapas();
		List<EtapaDTO> etapasDTO = new ArrayList<EtapaDTO>();
		for (Etapa etapa : etapas) {
			etapasDTO.add(converterEtapaParaDTO(etapa));
		}
		
		itemDTO.setListaEtapas(etapasDTO);
		
		return itemDTO;
	}
}

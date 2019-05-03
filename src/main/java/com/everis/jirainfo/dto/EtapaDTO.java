package com.everis.jirainfo.dto;

import java.io.Serializable;
import java.util.Calendar;

public class EtapaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459684140602528511L;

	private Long id;
	private String statusInicial;
	private Calendar dataStatusInicial;
	private Double tempoStatusInicial;
	private String statusFinal;
	private Double tempoStatusFinal;
	private String chaveItem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatusInicial() {
		return statusInicial;
	}
	public void setStatusInicial(String statusInicial) {
		this.statusInicial = statusInicial;
	}
	public Calendar getDataStatusInicial() {
		return dataStatusInicial;
	}
	public void setDataStatusInicial(Calendar dataStatusInicial) {
		this.dataStatusInicial = dataStatusInicial;
	}
	public Double getTempoStatusInicial() {
		return tempoStatusInicial;
	}
	public void setTempoStatusInicial(Double tempoStatusInicial) {
		this.tempoStatusInicial = tempoStatusInicial;
	}
	public String getStatusFinal() {
		return statusFinal;
	}
	public void setStatusFinal(String statusFinal) {
		this.statusFinal = statusFinal;
	}
	public Double getTempoStatusFinal() {
		return tempoStatusFinal;
	}
	public void setTempoStatusFinal(Double tempoStatusFinal) {
		this.tempoStatusFinal = tempoStatusFinal;
	}
	public String getChaveItem() {
		return chaveItem;
	}
	public void setChaveItem(String chaveItem) {
		this.chaveItem = chaveItem;
	}

}

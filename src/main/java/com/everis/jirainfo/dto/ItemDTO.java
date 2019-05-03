package com.everis.jirainfo.dto;

import java.io.Serializable;
import java.util.List;

public class ItemDTO implements Serializable {
	private String chave;
	private String resumo;
	private String responsavel;
	private Integer pontos;
	private String situacao;
	private String tipo;
	private List<EtapaDTO> listaEtapas;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<EtapaDTO> getListaEtapas() {
		return listaEtapas;
	}

	public void setListaEtapas(List<EtapaDTO> listaEtapas) {
		this.listaEtapas = listaEtapas;
	}
}

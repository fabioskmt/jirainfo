package com.everis.jirainfo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 3, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("IT")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8150339739577743823L;
	
	@Id
	private String chave;
	@Column
	private String resumo;
	@Column
	private String responsavel;
	
	@Column 
	private Integer pontos;

	@Column
	private String situacao;
	@Column(insertable=false, updatable=false)
	private String tipo;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<Etapa> listaEtapas;

	public Item() {		
	}
	
	public Item(String chave, String resumo, String responsavel, String situacao) {
		super();
		this.chave = chave;
		this.resumo = resumo;
		this.responsavel = responsavel;
		this.situacao = situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	@Override
	public String toString() {
		return "Subtask [chave=" + chave + ", resumo=" + resumo + ", responsavel=" + responsavel + ", situacao="
				+ situacao + "]";
	}

	public List<Etapa> getListaEtapas() {
		return listaEtapas;
	}

	public void setListaEtapas(List<Etapa> listaEtapas) {
		this.listaEtapas = listaEtapas;
	}
	
}

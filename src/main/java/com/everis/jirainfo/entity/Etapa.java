package com.everis.jirainfo.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(name = "etapa")
public class Etapa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3159431697476375782L;


	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="status_inicial")
	private String statusInicial;
	@Column(name="data_status_inicial")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataStatusInicial;
	@Column(name="tempo_status_inicial")
	private Double tempoStatusInicial;
	@Column(name="status_final")
	private String statusFinal;
	@Column(name="tempo_status_final")
	private Double tempoStatusFinal;
	
	@ManyToOne
	@JoinColumn
	private Item item;

	public Etapa(String statusInicial, Calendar dataStatusInicial, double tempoStatusInicial, String statusFinal,
			double tempoStatusFinal) {
		super();
		this.statusInicial = statusInicial;
		this.dataStatusInicial = dataStatusInicial;
		this.tempoStatusInicial = tempoStatusInicial;
		this.statusFinal = statusFinal;
		this.tempoStatusFinal = tempoStatusFinal;
	}

	public Etapa() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getStatusInicial() {
		return statusInicial;
	}

	public void setStatusInicial(String statusInicial) {
		this.statusInicial = statusInicial;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	public Calendar getDataStatusInicial() {
		return dataStatusInicial;
	}

	public void setDataStatusInicial(Calendar dataStatusInicial) {
		this.dataStatusInicial = dataStatusInicial;
	}

	public Double getTempoStatusInicial() {
		return tempoStatusInicial;
	}

	public void setTempoStatusInicial(double tempoStatusInicial) {
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

	public void setTempoStatusFinal(double tempoStatusFinal) {
		this.tempoStatusFinal = tempoStatusFinal;
	}

	@Override
	public String toString() {
		return "Etapa [statusInicial=" + statusInicial + ", dataStatusInicial=" + dataStatusInicial
				+ ", tempoStatusInicial=" + tempoStatusInicial + ", statusFinal=" + statusFinal + ", tempoStatusFinal="
				+ tempoStatusFinal + "]";
	}
}

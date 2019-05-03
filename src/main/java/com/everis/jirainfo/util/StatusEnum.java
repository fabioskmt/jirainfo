package com.everis.jirainfo.util;

public enum StatusEnum {
	
	EM_PROGRESSO("Em progresso"),
	ABERTO("Aberto"),
	REABERTO("Reaberto"),
	RESOLVIDO("Resolvido"),
	BLOQUEADO("Bloqueado");
	
	private String status;	
	
	StatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

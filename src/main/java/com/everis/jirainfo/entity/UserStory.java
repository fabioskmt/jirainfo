package com.everis.jirainfo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value = "US")
public class UserStory extends Item {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2482340259865694575L;
	
	@OneToMany(mappedBy = "userstory", cascade = CascadeType.ALL)
	private List<Subtask> listaSubtask;
	
	public List<Subtask> getListaSubtask() {
		return listaSubtask;
	}

	public void setListaSubtask(List<Subtask> listaSubtask) {
		this.listaSubtask = listaSubtask;
	}
}

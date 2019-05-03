package com.everis.jirainfo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue(value = "ST")
public class Subtask extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6999643269147937353L;
	
	@ManyToOne
	@JoinColumn
	private UserStory userstory;

	public UserStory getUserstory() {
		return userstory;
	}

	public void setUserstory(UserStory userstory) {
		this.userstory = userstory;
	}	
}

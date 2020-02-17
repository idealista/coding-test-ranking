package com.idealista.application.exception.dto;

import com.idealista.application.bean.Level;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorDTO implements Serializable {
	private String field;
	private String description;
	private Level level;

	public ErrorDTO(String field, String description){
		this.field = field;
		this.description = description;
	}

	public ErrorDTO(String field, String description, Level level){
		this.field = field;
		this.description = description;
		this.level = level;
	}

	public ErrorDTO(){}
}
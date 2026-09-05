package com.loja.newloja.infra.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class StandardErrorDto {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	private List<FieldErrorDto> fieldErrors;

	public StandardErrorDto(LocalDateTime timestamp, Integer status, String error, String message, String path, List<FieldErrorDto> fieldErrors) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.fieldErrors = fieldErrors;
	}

	public StandardErrorDto(LocalDateTime now, int value, String nãoAutorizado, String message, String requestURI) {
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FieldErrorDto> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}

package com.loja.newloja.infra.exceptions;

import java.time.Instant;
import java.util.List;

public record ValidationError(
				Instant timestamp,
				Integer status,
				String error,
				String message,
				String path,
				List<FieldErrorDTO> errors
) {
	public record FieldErrorDTO(String field, String message) {}
}

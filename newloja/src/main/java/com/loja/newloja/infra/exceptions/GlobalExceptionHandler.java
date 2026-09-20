package com.loja.newloja.infra.exceptions;

import com.loja.newloja.infra.model.dto.FieldErrorDto;
import com.loja.newloja.infra.model.dto.StandardErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardErrorDto> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;

		List<FieldErrorDto> fieldErrors = new ArrayList<>();
		for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
			fieldErrors.add(new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()));
		}

		StandardErrorDto error = new StandardErrorDto(
						LocalDateTime.now(),
						status.value(),
						"Erro de Validação de Dados",
						"Um ou mais campos enviados estão inválidos.",
						request.getRequestURI(),
						fieldErrors
		);
		return ResponseEntity.status(status).body(error);
	}

	//Captura erros de login (BadCredentialsException que jogamos no login)
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardErrorDto> handBadCredentials(BadCredentialsException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNAUTHORIZED;

		StandardErrorDto error = new StandardErrorDto(
						LocalDateTime.now(),
						status.value(),
						"Não Autorizado",
						ex.getMessage(),
						request.getRequestURI()
		);
		return ResponseEntity.status(status).body(error);
	}

	//Captura qualquer outro erro inesperado na aplicação (fallback de Segurança)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardErrorDto> handleGenericException(Exception ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		StandardErrorDto error = new StandardErrorDto(
						LocalDateTime.now(),
						status.value(),
						"Error Interno no Servidor",
						"Ocorreu um erro imprevisto em nosso sistema. Por favor, tente novamente mais tarde.",
						request.getRequestURI()
		);
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardErrorDto error = new StandardErrorDto(
						LocalDateTime.now(),
						status.value(),
						"Não Encontrado",
						ex.getMessage(),
						request.getRequestURI()
		);
		return ResponseEntity.status(status).body(error);
	}
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	public ResponseEntity<StandardErrorDto> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400!

		StandardErrorDto error = new StandardErrorDto(
						LocalDateTime.now(),
						status.value(),
						"Corpo da Requisição Inválido",
						"Erro ao processar as propriedades do JSON. Verifique o formato dos campos (como datas ou enums).",
						request.getRequestURI()
		);
		return ResponseEntity.status(status).body(error);
	}
}

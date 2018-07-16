package sample.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleing
{
	@ExceptionHandler(Exception.class)
	public String spittleNotFoundHandler() {
		return "Error";
	}
}

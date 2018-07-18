package sample.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHandleing.
 */
@ControllerAdvice
public class ExceptionHandleing
{

	/**
	 * Spittle not found handler.
	 *
	 * @return the string
	 */
	@ExceptionHandler(Exception.class)
	public String spittleNotFoundHandler() {
		return "Error";
	}
}

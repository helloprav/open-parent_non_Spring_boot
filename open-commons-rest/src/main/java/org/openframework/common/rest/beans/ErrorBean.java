package org.openframework.common.rest.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by francesco on 2/11/14.
 */
@JsonInclude(Include.NON_NULL)             // Jackson 2 or higher
public class ErrorBean extends ValidationBean {

	// TODO add following here; take idea from https://springuni.com/user-management-microservice-part-4/
	// private int statusCode;
	private String errorCode;
	private String errorMessage;

	public ErrorBean() {
		// no arg constructor
	}

	public ErrorBean(String message) {
		this.errorMessage=message;
	}

	public ErrorBean(String code, String message) {
		this.errorCode=code;
		this.errorMessage=message;
	}

	public ErrorBean(String code, String message, List<String> errors) {
		super(errors);
		this.errorCode=code;
		this.errorMessage=message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

/**
 * 
 */
package org.openframework.commons.domain.exceptions;

/**
 * @author Java Developer
 *
 */
public class AuthenticationException extends ApplicationRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7241355620740425238L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

}

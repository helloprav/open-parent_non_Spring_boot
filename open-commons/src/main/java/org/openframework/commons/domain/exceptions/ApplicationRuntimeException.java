/**
 * 
 */
package org.openframework.commons.domain.exceptions;

/**
 * @author Java Developer
 *
 */
public class ApplicationRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5076424288173160260L;

	public ApplicationRuntimeException() {
		// no args constructor
	}

	public ApplicationRuntimeException(String message) {
		super(message);
	}

	public ApplicationRuntimeException(Throwable cause) {
		super(cause);
	}

	public ApplicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		//
	}

}

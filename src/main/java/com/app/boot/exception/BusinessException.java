package com.app.boot.exception;

/**
 * @author zayneb
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String codeError;

	/**
	 * 
	 */
	public BusinessException(final String codeError) {
		this.codeError = codeError;
	}

	/**
	 * @param message
	 */
	public BusinessException(final String codeError, String message) {
		super(message);
		this.codeError = codeError;
	}

	/**
	 * @param cause
	 */
	public BusinessException(final String codeError, Throwable cause) {
		super(cause);
		this.codeError = codeError;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(final String codeError, String message, Throwable cause) {
		super(message, cause);
		this.codeError = codeError;
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BusinessException(final String codeError, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.codeError = codeError;
	}

	/**
	 * @return the codeError
	 */
	public String getCodeError() {
		return codeError;
	}

}

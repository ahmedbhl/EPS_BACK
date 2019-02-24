package com.app.boot.exception;

/**
 * 
 * @author zayneb
 *
 */
public class CodeOperationException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Message
	 */
	private static final String MESSAGE_FORMATTER_ID = "Unknow Code %s";

	public enum CodeError {
		CODE_NOT_FOUND
	}

	/**
	 *
	 * @param codeError
	 * @param id
	 */
	public CodeOperationException(String codeError, String id) {
		super(codeError, String.format(MESSAGE_FORMATTER_ID, id));
	}

	/**
	 * @param codeError
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CodeOperationException(Enum<?> codeError, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(codeError.name(), message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param codeError
	 * @param message
	 * @param cause
	 */
	public CodeOperationException(Enum<?> codeError, String message, Throwable cause) {
		super(codeError.name(), message, cause);
	}

	/**
	 * @param codeError
	 * @param message
	 */
	public CodeOperationException(Enum<?> codeError, String message) {
		super(codeError.name(), message);
	}

	/**
	 * @param codeError
	 * @param cause
	 */
	public CodeOperationException(Enum<?> codeError, Throwable cause) {
		super(codeError.name(), cause);
	}

	/**
	 * @param codeError
	 */
	public CodeOperationException(Enum<?> codeError) {
		super(codeError.name());
	}
}

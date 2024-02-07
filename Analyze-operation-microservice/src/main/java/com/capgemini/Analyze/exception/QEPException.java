package com.capgemini.Analyze.exception;

import com.capgemini.Analyze.dto.ResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class QEPException extends Exception {
	private static final String EXCEPTION_MESSAGE = "Exception is ";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(QEPException.class);


	public QEPException() {
		super();
	}

	public QEPException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QEPException(String message, Throwable cause) {
		super(message, cause);
	}

	public QEPException(String message) {
		super(message);
	}

	public QEPException(Throwable cause) {
		super(cause);
	}
	public static ResponseDTO exception(Exception e){
		ResponseDTO responseDTO=new ResponseDTO();
		logger.info(EXCEPTION_MESSAGE + e.getMessage());
		responseDTO.setException(e.getMessage());
		responseDTO.setMessage("Fail");
		return responseDTO;
	}
}

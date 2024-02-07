package com.capgemini.Analyze.util;

import com.capgemini.Analyze.exception.QEPException;

public class Constants {
	 private Constants() throws QEPException {
		    throw new QEPException("Utility class");
		  }
	/**Database Config**/
	public static final int MAX_ACTIVE = 2;
	public static final int MAX_AGE = 1000;
	public static final int IDLE_TIME = 60000;
	
	/**Logging**/
	public static final String RUNNINGMETHOD = "Running method is.......";
	
	public static final String LINE_SEPARATOR = "line.separator";
	public static final String BREAK = "<br/>";
	public static final String SUCCESS = "success";
	public static final String ADMIN="Admin";  
	public static final String CAPABILITYNOTFOUND="CAPABILITY NOT FOUND";
	public static final String FROMNAME="QET TEAM";
	public static final String DLCORE="QET TEAM";
	

	public static final int NUMBER_OF_NESTED_OBJECTS = 7;
}



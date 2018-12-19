package com.accenture.tcf.bars.exception;

public class BarsException extends Exception{
	public final static String INVALID_START_DATE_FORMAT = "“ERROR: Invalid Start Date format at row ”";//
	public final static String INVALID_END_DATE_FORMAT = "ERROR: Invalid End Date format at row ";//
	public final static String BILLING_CYCLE_NOT_ON_RANGE  = "ERROR: Billing Cycle not on range at row ";//
	public final static String PATH_DOES_NOT_EXIST = "Please input an existing request file path.";//
	public final static String NO_SUPPORTED_FILE = "No supported request file found in the file path";//
	public final static String NO_RECORDS_TO_READ = "No request(s) to read from the input file.”";//
	public final static String NO_RECORDS_TO_WRITE = "No record(s) to write to the output file.";
	
	public BarsException(String message) {
		super(message);
	}
	
	public BarsException(String message, Throwable cause) {
		super(message,cause);
	}
	
}

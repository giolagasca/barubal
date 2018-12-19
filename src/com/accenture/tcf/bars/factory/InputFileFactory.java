package com.accenture.tcf.bars.factory;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.tcf.bars.file.CSVInputFileImpl;
import com.accenture.tcf.bars.file.IInputFile;
import com.accenture.tcf.bars.file.TextInputFileImpl;

public class InputFileFactory {
	private TextInputFileImpl txtInputFile;
	
	private CSVInputFileImpl csvInputFile;
	
	private InputFileFactory() {
	}
	
	public static InputFileFactory getInstance() {
		InputFileFactory inputFileFactory = new InputFileFactory();
		return inputFileFactory;
	}
	
	public IInputFile getInputFile(File file) {
		String[] split = file.getName().split("\\.");
		if(split[split.length-1].equalsIgnoreCase("csv")) {
			csvInputFile = new CSVInputFileImpl();
			csvInputFile.setFile(file);
			return csvInputFile;
		}
		else if(split[split.length-1].equalsIgnoreCase("txt")) {
			txtInputFile = new TextInputFileImpl();
			txtInputFile.setFile(file);
			return txtInputFile;
		}
		return null;
		
	}
}

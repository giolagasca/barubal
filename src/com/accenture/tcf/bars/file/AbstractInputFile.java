package com.accenture.tcf.bars.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;

abstract class AbstractInputFile implements IInputFile {
	
	private File file;

	@Override
	public List<Request> readFile() throws Exception {
		return null;
	}	
	
	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}

}
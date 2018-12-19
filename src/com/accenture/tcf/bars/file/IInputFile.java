package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;

public interface IInputFile {
	public List<Request> readFile() throws Exception;
	public void setFile(File file);
	public File getFile();
}

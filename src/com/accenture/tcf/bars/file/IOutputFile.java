package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public interface IOutputFile {
	public void writeFile(List<Record> records) throws BarsException;
	public void setFile(File file);
	public File getFile();
}

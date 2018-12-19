package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public abstract class AbstractOutputFile implements IOutputFile {
	private File file;
	//protected Logger logger = LoggerFactory
	

	@Override
	public void writeFile(List<Record> records) throws BarsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFile(File file) {
		file=this.file;
	}

	@Override
	public File getFile() {
		return this.file;
	}
}

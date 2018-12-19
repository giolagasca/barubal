package com.accenture.tcf.bars.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;

import com.accenture.tcf.bars.factory.InputFileFactory;
import com.accenture.tcf.bars.file.CSVInputFileImpl;
import com.accenture.tcf.bars.file.TextInputFileImpl;

public class InputFileFactoryTest {
	
	@Test
	public void testGetInstance() {
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		
		assertNotNull(inputFileFactory);	
	}
	
	@Test
	public void testGetInputFileTxt() {
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();	
		assertThat(inputFileFactory.getInputFile(new File("C:\\Users\\tr_lnd_user\\Documents\\t.txt")), instanceOf(TextInputFileImpl.class));
	}
	
	@Test
	public void testGetInputFileCsvl() {
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		
		assertThat(inputFileFactory.getInputFile(new File("C:\\Users\\tr_lnd_user\\Documents\\test.csv")), instanceOf(CSVInputFileImpl.class));
		
	}
	
	@Test
	public void testGetInputFileNull() {
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		
		assertNull("Tite", inputFileFactory.getInputFile(new File ("C:\\Users\\tr_lnd_user\\Documents\\egg.docx")));
	}

}

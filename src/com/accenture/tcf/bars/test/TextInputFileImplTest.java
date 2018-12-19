package com.accenture.tcf.bars.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.factory.InputFileFactory;
import com.accenture.tcf.bars.file.IInputFile;

public class TextInputFileImplTest {
	
	@Test
	public void testReadTextValidRequestParameter() throws Exception {
		String startDate = "01012013";
		String endDate = "01312013";
		InputFileFactory inputFileFactory = InputFileFactory.getInstance();
		IInputFile inputFile = inputFileFactory.getInputFile(new File("C:\\Users\\tr_lnd_user\\Documents\\TestFiles\\test1.txt"));
		List<Request> expectedList = new ArrayList<>();
		expectedList.add(new Request(Integer.parseInt("01"), new SimpleDateFormat("MMddyyyy").parse(startDate), new SimpleDateFormat("MMddyyyy").parse(endDate)));
		List<Request> actualList = inputFile.readFile();
		
		assertEquals(expectedList.get(0).getBillingCycle(), actualList.get(0).getBillingCycle());
		assertEquals(expectedList.get(0).getStartDate(), actualList.get(0).getStartDate());
		assertEquals(expectedList.get(0).getEndDate(), actualList.get(0).getEndDate());
	}
	
	@Test
	public void testReadTextInvalidBillingCycle() {
		
	}

}
	
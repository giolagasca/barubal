package com.accenture.tcf.bars.dao;

import java.util.List;

import com.accenture.tcf.bars.domain.Record;

public interface IRecordDAO {
	public List<Record> retrieveRecords();
}

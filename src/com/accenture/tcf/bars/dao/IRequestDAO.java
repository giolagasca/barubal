package com.accenture.tcf.bars.dao;

import com.accenture.tcf.bars.domain.Request;

public interface IRequestDAO {
	public void insertRequest(Request request);
	public void deleteRequest();
}

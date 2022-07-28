package com.jocata.hrms.dao;

import org.springframework.stereotype.Component;

import com.jocata.hrms.entity.EmployeeDocumentList;

@Component
public interface DocumentInterface {
	
	public Object save(EmployeeDocumentList entity);
	public Object getDocList(EmployeeDocumentList entity);
}

package com.jocata.hmrs.dao;

import org.springframework.stereotype.Component;

import com.jocata.hmrs.entity.EmployeeDocumentList;

@Component
public interface DocumentInterface {
	
	public Object save(EmployeeDocumentList entity);
	public Object getDocList(EmployeeDocumentList entity);
	Object getDocList(String empId);
}

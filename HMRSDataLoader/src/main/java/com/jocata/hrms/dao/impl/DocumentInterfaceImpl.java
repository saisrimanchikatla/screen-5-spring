package com.jocata.hrms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jocata.hrms.dao.DocumentInterface;
import com.jocata.hrms.dao.HibernateUtils;
import com.jocata.hrms.entity.DocumentType;
import com.jocata.hrms.entity.EmployeeDocumentList;

@Component
public class DocumentInterfaceImpl implements DocumentInterface {
	
	@Autowired
	HibernateUtils hibernateUtils;

	@Override
	public Object save(EmployeeDocumentList entity) {
		// TODO Auto-generated method stub
		return hibernateUtils.save(entity);
	}
	
	public DocumentType getByDocType(DocumentType documentType)
	{
		String hql="From DocumentType where doctype="+documentType.getDoctype();
		List<DocumentType> cdlist= hibernateUtils.loadEntityByHql(hql);
		if(cdlist!=null && cdlist.size()>0) return cdlist.get(0);
		return documentType;
	}

	@Override
	public List<EmployeeDocumentList> getDocList(EmployeeDocumentList entity) {
		// TODO Auto-generated method stub
		String hql="From DocumentType where doctype="+entity.getEmpid();
		List<EmployeeDocumentList> cdlist= hibernateUtils.loadEntityByHql(hql);
		
		return cdlist;
	}
	
	

}

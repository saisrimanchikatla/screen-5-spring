package com.jocata.hmrs.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jocata.hmrs.dao.DocumentInterface;
import com.jocata.hmrs.dao.HibernateUtils;
import com.jocata.hmrs.entity.DocumentType;
import com.jocata.hmrs.entity.EmployeeDoc;
import com.jocata.hmrs.entity.EmployeeDocumentList;

@Component
public class DocumentInterfaceImpl implements DocumentInterface {
	
	@Autowired
	HibernateUtils hibernateUtils;

	@Override
	public Object save(EmployeeDocumentList entity) {
		// TODO Auto-generated method stub
		return hibernateUtils.save(entity);
	}
	
	public Object getByDocTypeAndEmpId(Integer documentType_Id,Integer empId)
	{
		String hql="From EmployeeDoc t where t.doc_type_id="+documentType_Id+" and t.emp_id ="+empId;
		return hibernateUtils.loadEntityByHql(hql);
		
	}

	@Override
	public Object getDocList(String empId) {
		// TODO Auto-generated method stub
		String hql="From EmployeeDoc where emp_id="+empId;
		return hibernateUtils.loadEntityByHql(hql);
	}

	@Override
	public Object getDocList(EmployeeDocumentList entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

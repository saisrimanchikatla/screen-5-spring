package com.jocata.hrms.request;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jocata.hrms.response.DocList;

@Component
@JsonIgnoreProperties
public class DocumentListRequest implements Serializable{


	private static final long serialVersionUID = 7540301887805659247L;
	
	private Integer empId;
	private  List<DocList> docList;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public List<DocList> getDocList() {
		return docList;
	}
	public void setDocList(List<DocList> docList) {
		this.docList = docList;
	}
	
	
	

}

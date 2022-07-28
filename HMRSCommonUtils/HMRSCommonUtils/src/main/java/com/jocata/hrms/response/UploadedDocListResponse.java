package com.jocata.hrms.response;

import java.io.Serializable;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties
public class UploadedDocListResponse implements Serializable{
	
	

	private static final long serialVersionUID = -1384719706629124761L;
	public Integer emp_id;
    public String status;
    public List<DocList> docList;
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DocList> getDocList() {
		return docList;
	}
	public void setDocList(List<DocList> docList) {
		this.docList = docList;
	}
    
    
    

}

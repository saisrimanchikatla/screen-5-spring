package com.jocata.hrms.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties
public class DownloadUploadedDocRequest implements Serializable{


	private static final long serialVersionUID = 2516369303712989279L;
	
	private Integer empId;
	private String docType;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
}

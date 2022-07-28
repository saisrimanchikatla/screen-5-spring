package com.jocata.hrms.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties
public class UploadedDocListFormRequest implements Serializable{
	

	private static final long serialVersionUID = -7546142902699087335L;
	private Integer empId;
	private String docType;
	private MultipartFile docData;
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
	public MultipartFile getDocData() {
		return docData;
	}
	public void setDocData(MultipartFile docData) {
		this.docData = docData;
	}
	
	

}

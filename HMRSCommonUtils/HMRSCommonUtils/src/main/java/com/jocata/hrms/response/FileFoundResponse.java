package com.jocata.hrms.response;

import java.io.Serializable;

public class FileFoundResponse implements Serializable{

	
	private static final long serialVersionUID = -3355738523495898628L;
	private boolean isfound;
	private String docType;
	private String empId;
	private boolean isUploaded; 
	public boolean isUploaded() {
		return isUploaded;
	}
	public void setIsUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}
	public boolean isIsfound() {
		return isfound;
	}
	public void setIsfound(boolean isfound) {
		this.isfound = isfound;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	

}

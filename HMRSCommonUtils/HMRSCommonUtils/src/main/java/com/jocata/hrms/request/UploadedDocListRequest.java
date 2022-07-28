package com.jocata.hrms.request;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Component
public class UploadedDocListRequest implements Serializable{


	private static final long serialVersionUID = -4407466884395888803L;
	
	private Integer empId;

	public Integer getEmpid() {
		return empId;
	}

	public void setEmpid(Integer empid) {
		this.empId = empid;
	}
	
	
	
	

}

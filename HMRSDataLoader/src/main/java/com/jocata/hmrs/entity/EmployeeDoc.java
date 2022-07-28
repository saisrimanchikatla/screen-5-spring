package com.jocata.hmrs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_emp_docs")
public class EmployeeDoc {
	private Integer id;
	private Integer doc_type_id;
	private Integer emp_id;
	private String doc_location;
	private String uploaded_date;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDoc_type_id() {
		return doc_type_id;
	}
	public void setDoc_type_id(Integer doc_type_id) {
		this.doc_type_id = doc_type_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getDoc_location() {
		return doc_location;
	}
	public void setDoc_location(String doc_location) {
		this.doc_location = doc_location;
	}
	public String getUploaded_date() {
		return uploaded_date;
	}
	public void setUploaded_date(String uploaded_date) {
		this.uploaded_date = uploaded_date;
	}

}

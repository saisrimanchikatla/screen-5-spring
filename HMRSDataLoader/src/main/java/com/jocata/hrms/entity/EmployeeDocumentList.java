package com.jocata.hrms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_emp_docs")
public class EmployeeDocumentList {
	
	    private Integer id;
	    
	    @Column(name="emp_id")
	    private Integer empid;
	    
	    @Column(name="doc_type")
	    private String doctype;
	    
	    @Column(name="doc_location")
	    private String doclocation;
	    
	    @Column(name="uploaded_date")
	    private String uploadeddate;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Integer getId() {
	        return id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
	    }
		public Integer getEmpid() {
			return empid;
		}
		public void setEmpid(Integer empid) {
			this.empid = empid;
		}
		
		public String getDoctype() {
			return doctype;
		}
		public void setDoctype(String doctype) {
			this.doctype = doctype;
		}
		public String getDoclocation() {
			return doclocation;
		}
		public void setDoclocation(String doclocation) {
			this.doclocation = doclocation;
		}
		public String getUploadeddate() {
			return uploadeddate;
		}
		public void setUploadeddate(String uploadeddate) {
			this.uploadeddate = uploadeddate;
		}
		
	   
}

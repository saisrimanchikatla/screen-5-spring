package com.jocata.hrms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jocata.hrms.dao.impl.DocumentInterfaceImpl;
import com.jocata.hrms.entity.DocumentType;
import com.jocata.hrms.entity.EmployeeDocumentList;
import com.jocata.hrms.request.DocumentListRequest;
import com.jocata.hrms.request.UploadedDocListFormRequest;
import com.jocata.hrms.request.UploadedDocListRequest;
import com.jocata.hrms.response.DocList;
import com.jocata.hrms.response.UploadedDocListFormResponse;
import com.jocata.hrms.response.UploadedDocListResponse;

@RestController
@CrossOrigin
@RequestMapping("/docs")
public class DocumentController {
	
	@Autowired
	DocumentInterfaceImpl documentInterfaceImpl;
	
	@Value("eos.doc.path") //keep this in application.properties as eos.doc.pf.path = D:\\EmpDocs\\
	String docPath;
	
	@RequestMapping(value = "/uploadDoc", method = RequestMethod.POST)
	public ResponseEntity<?> uploadedDocList(@RequestBody UploadedDocListFormRequest uploadedDocListFormRequest) throws IOException
	{
		
		Integer empId=uploadedDocListFormRequest.getEmpId();
		String docType=uploadedDocListFormRequest.getDocType();
		FileInputStream docFileInput = null;

		byte[] filedataArray = null;
		String encoded = "";
		String completeFilePath = null;
		

		completeFilePath = docPath + docType + "_" + empId + ".pdf";
		
		Path absoluteFilePath = Paths.get(completeFilePath);
		Files.deleteIfExists(absoluteFilePath);
		Path docFilePath = null;
		
		docFilePath = Files.createFile(absoluteFilePath);
		
		Files.copy(uploadedDocListFormRequest.getDocData().getInputStream(),absoluteFilePath , StandardCopyOption.REPLACE_EXISTING);
		
		JSONObject successResp = new JSONObject("{}");
		
		
        successResp.put("docPath",completeFilePath);
        successResp.put("docType",docType);
        successResp.put("status","success");
        successResp.put("empId",empId);
        return new ResponseEntity<>(successResp, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/saveuploadDoc", method = RequestMethod.POST)
	public ResponseEntity<UploadedDocListFormResponse> saveUploadedDocList(@RequestBody DocumentListRequest documentListRequest)
	{
	
		EmployeeDocumentList employeeDocumentList=new EmployeeDocumentList();
				
		employeeDocumentList.setEmpid(documentListRequest.getEmpId());
		
		//ArrayList<DocList> doc=new ArrayList<DocList>();
		
		ArrayList<DocList> doclist=(ArrayList<DocList>) documentListRequest.getDocList();
		for(DocList d:doclist)
		{
			employeeDocumentList.setDoclocation(d.getDocumentPath());
			employeeDocumentList.setDoctype(d.getDocumentTitle());
			Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String strDate= formatter.format(date);  
		    employeeDocumentList.setUploadeddate(strDate); 
		    
		  Integer id= (Integer)documentInterfaceImpl.save(employeeDocumentList);
		  //employeeDocumentList.setId(id);
		  
		}
		
		UploadedDocListFormResponse uploadedDocListFormResponse=new UploadedDocListFormResponse();
		uploadedDocListFormResponse.setEmpid(documentListRequest.getEmpId());
		uploadedDocListFormResponse.setStatus("Success");
		uploadedDocListFormResponse.setStage("Document_List");
		return new ResponseEntity<UploadedDocListFormResponse>(uploadedDocListFormResponse,HttpStatus.OK);
	}
	
	
	public ResponseEntity<UploadedDocListResponse> getEmployeeDocList(@RequestBody UploadedDocListRequest uploadedDocListRequest)
	{
		EmployeeDocumentList employeeDocumentList=new EmployeeDocumentList();
		employeeDocumentList.setEmpid(uploadedDocListRequest.getEmpid());
		List<EmployeeDocumentList> elist=documentInterfaceImpl.getDocList(employeeDocumentList);
		
		UploadedDocListResponse uploadedDocListResponse=new UploadedDocListResponse();
		uploadedDocListResponse.setEmp_id(uploadedDocListRequest.getEmpid());
		uploadedDocListResponse.setStatus("Success");
		List<DocList> dlist=new ArrayList<DocList>();
		for(EmployeeDocumentList e:elist)
		{
			DocList d=new DocList();
			d.setDocumentPath(e.getDoclocation());
			d.setDocumentTitle(e.getDoctype());
			dlist.add(d);
		}
		
		uploadedDocListResponse.setDocList(dlist);
		
		return new ResponseEntity<UploadedDocListResponse>(uploadedDocListResponse,HttpStatus.OK);
		
	}

}

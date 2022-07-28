package com.jocata.hmrs.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jocata.hmrs.dao.impl.DocumentInterfaceImpl;
import com.jocata.hmrs.entity.DocumentType;
import com.jocata.hmrs.entity.EmployeeDoc;
import com.jocata.hmrs.entity.EmployeeDocumentList;
import com.jocata.hrms.request.DocumentListRequest;
import com.jocata.hrms.request.DownloadUploadedDocRequest;
import com.jocata.hrms.request.UploadedDocListFormRequest;
import com.jocata.hrms.request.UploadedDocListRequest;
import com.jocata.hrms.response.DocList;
import com.jocata.hrms.response.DowloadUploadedDocResponse;
import com.jocata.hrms.response.UploadedDocListFormResponse;
import com.jocata.hrms.response.UploadedDocListResponse;


@CrossOrigin
@RestController
@RequestMapping("/docs")
public class DocumentController {
	
	@Autowired
	DocumentInterfaceImpl documentInterfaceImpl;
	
	@Value("D:\\EmpDocs\\") //keep this in application.properties as eos.doc.pf.path = D:\\EmpDocs\\
	String docPath;
	
	private final org.jboss.logging.Logger logger= LoggerFactory.logger(this.getClass());

	/*@RequestMapping(value = "/uploadDoc", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
	        produces = {MediaType.APPLICATION_JSON_VALUE})
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
				
		employeeDocumentList.setEmp_id(documentListRequest.getEmpId());
		
		//ArrayList<DocList> doc=new ArrayList<DocList>();
		
		ArrayList<DocList> doclist=(ArrayList<DocList>) documentListRequest.getDocList();
		for(DocList d:doclist)
		{
			employeeDocumentList.setDoc_location(d.getDocumentPath());
			employeeDocumentList.setDoc_type_id(d.getDocumentTitle());
			Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String strDate= formatter.format(date);  
		    employeeDocumentList.setUploaded_date(strDate); 
		    
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
		employeeDocumentList.setEmp_id(uploadedDocListRequest.getEmpid());
		List<EmployeeDocumentList> elist=documentInterfaceImpl.getDocList(employeeDocumentList);
		
		UploadedDocListResponse uploadedDocListResponse=new UploadedDocListResponse();
		uploadedDocListResponse.setEmp_id(uploadedDocListRequest.getEmpid());
		uploadedDocListResponse.setStatus("Success");
		List<DocList> dlist=new ArrayList<DocList>();
		for(EmployeeDocumentList e:elist)
		{
			DocList d=new DocList();
			d.setDocumentPath(e.getDoc_location());
			d.setDocumentTitle(e.getDoc_type_id());
			dlist.add(d);
		}
		
		uploadedDocListResponse.setDocList(dlist);
		
		return new ResponseEntity<UploadedDocListResponse>(uploadedDocListResponse,HttpStatus.OK);
		
	}
	*/
	
	
	
	@Configuration
	@EnableWebMvc
	public class CourseConfig implements WebMvcConfigurer {

	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	      registry.addMapping("/**")
	              .allowedOrigins("*")
	              .allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE")
	              .maxAge(3600);
	  }
	}
	
    @PostMapping(value = "/upload"
        //consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    
        //produces = {MediaType.APPLICATION_JSON_VALUE}
        )
    public ResponseEntity<?> uploadFileWithParameter(@RequestParam MultipartFile file,@RequestParam Integer empId,@RequestParam String docType) throws IOException {
        System.out.println("File upload with parameter:" + file+"\n"+empId+"\n"+docType);byte[] filedataArray = null;
		
        String completeFilePath = docPath + docType + "_" + empId + ".pdf";
		logger.info("file path"+completeFilePath);
		Path absoluteFilePath = Paths.get(completeFilePath);
		MultipartFile file1=file;
		Files.copy(file.getInputStream(), absoluteFilePath,StandardCopyOption.REPLACE_EXISTING);
		logger.info("file copied");
		
		EmployeeDocumentList employeeDocumentList=new EmployeeDocumentList();
		
		employeeDocumentList.setEmp_id(empId);
		employeeDocumentList.setDoc_location(completeFilePath);
		employeeDocumentList.setDoc_type_id(docType);
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
	    employeeDocumentList.setUploaded_date(strDate); 
	    
	    
	    Integer id= (Integer)documentInterfaceImpl.save(employeeDocumentList);
	    logger.info("location updated in mysql with id "+id);
	    
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    
	    logger.info("file link generated");
	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    			.path(completeFilePath)
    			.toUriString();
    	return ResponseEntity.ok(fileDownloadUri);
	    /*JSONObject successResp = new JSONObject("{}");
		
		
        successResp.put("docPath",completeFilePath);
        successResp.put("docType",docType);
        successResp.put("status","success");
        successResp.put("empId",empId);
        return new ResponseEntity<JSONObject>(successResp, HttpStatus.OK);*/
    }
      
    @RequestMapping(value="download/{empId}/{docType}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadFileFromLocal(@PathVariable("empId") Integer empId,@PathVariable("docType") String docType) throws IOException {
    	/*logger.info(downloadUploadedDocRequest.toString());
    	String docType=downloadUploadedDocRequest.getDocType();
    	Integer empId=downloadUploadedDocRequest.getEmpId();*/
    	
    	Path path = Paths.get(docPath + docType+"_"+empId);
    	FileReader fr=new FileReader(docPath + docType+"_"+empId+".pdf");
    	BufferedReader br=new BufferedReader(fr);
    	String s="";
    	String contentLine = br.readLine();
 	   while (contentLine != null) {
 		  s+=contentLine;
 	      System.out.println(contentLine);
 	      contentLine = br.readLine();
 	   }
    	logger.info("file data recieved");
    	
    	InputStreamResource resource=null;
    	
    	/*try {
    		ArrayList a= (ArrayList) documentInterfaceImpl.getByDocTypeAndEmpId(Integer.parseInt(docType), empId);
    		EmployeeDoc details=(EmployeeDoc) a.get(0);
    		String docDowloadLoc=details.getDoc_location();
    		logger.info("Got document location");
            resource = new InputStreamResource(new FileInputStream(docDowloadLoc));

    	} catch (Exception e) {
    		logger.error("File/Location Not Found");
    		e.printStackTrace();
    	}*/
    	DowloadUploadedDocResponse response=new DowloadUploadedDocResponse();
    	response.setEmpId(empId.toString());
    	response.setDocType(docType);
    	response.setFile(s);
    	
    	return new ResponseEntity<DowloadUploadedDocResponse>(response,HttpStatus.OK);
    	//return ResponseEntity.ok(file);
    }
}

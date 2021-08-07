package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.domain.Applicant;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/webadmin")
public class WebAdminController {
	
	@Autowired
	UserService uservice;
	
	@GetMapping("/list")
	public List<Applicant> allApplicant(){
		return uservice.findAllApplicant();
	}
	
	@GetMapping("list/applicant/approve")
	public List<Applicant> allApproveApplicant(){
		return uservice.findAllApplicantByApproveStatus();
	}
	
	@GetMapping("list/applicant/block")
	public List<Applicant> allBlockApplicant(){
		return uservice.findAllApplicantByBlockStatus();
	}
	
	@PutMapping("/list/applicant/{id}/{status}")
	public ResponseEntity<HttpStatus> updateApplicantStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
		try {
			uservice.updateApplicantStatus(id,status);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}

}

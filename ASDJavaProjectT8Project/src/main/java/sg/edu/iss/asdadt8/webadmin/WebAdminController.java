package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Review;

//@CrossOrigin(origins= "http://localhost:3000")
@CrossOrigin
@RestController
@RequestMapping("/api/webadmin")
public class WebAdminController {
	
	@Autowired
	ReviewServiceWeb rservice;
	
	@Autowired
	UserService uservice;
	
	@GetMapping("/list/applicant")
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
	
	@GetMapping("list/applicant/reported")
	public List<Applicant> allSuspiciousApplicant(){
		return uservice.findAllApplicantByReportedStatus();
	}
	
	@GetMapping("/list/applicant/{id}/{status}")
	public ResponseEntity<HttpStatus> updateApplicantStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
		try {
			uservice.updateApplicantStatus(id,status);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("list/reviews")
	public List<Review> findAllReviews() {
		return rservice.findAllReviews();
    }
	
	@GetMapping("list/reviews/rejected")
	public List<Review> findBlockedReviews() {
		return rservice.findBlockedReviews();
    }
	
	@GetMapping("list/reviews/approved")
	public List<Review> findApprovedReviews() {
		return rservice.findApprovedReviews();
    }
	
	@GetMapping("list/reviews/reported")
	public List<Review> findReportedReviews() {
		return rservice.findReportedReviews();
    }
	
	@GetMapping("list/reviews/{reviewid}/{reviewstatus}")
	public ResponseEntity<HttpStatus> updateReview(@PathVariable("reviewid") Long id, @PathVariable("reviewstatus") String status) {
	    //do here
		try {
			rservice.updateReviewStatus(id, status);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) { 
			  //return ResponseEntity.notFound().build(); 
			  return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			  } 
		//return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
		
	}
	
}
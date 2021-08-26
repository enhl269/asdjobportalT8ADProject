package sg.edu.iss.asdadt8.review;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import sg.edu.iss.asdadt8.DTOs.CompaniesReviewDTO;
import sg.edu.iss.asdadt8.DTOs.ReviewDTO;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.domain.ViewedJobs;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;
import sg.edu.iss.asdadt8.repositories.ReviewRepository;
import sg.edu.iss.asdadt8.webuser.WebUserJobDTO;

//@CrossOrigin(origins= "http://localhost:3000")
@CrossOrigin
@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	@Autowired
	ReviewService rservice;
    
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	
    @GetMapping("list")
	public List<ReviewDTO> allReview() {
		return rservice.findApprovedReviews();
    }
	
	@GetMapping("/company/{name}")
	public List<Company> allCompanies(@PathVariable("name") String companyName) {
        return crepo.findByCompanyName(companyName);
    }
	
	@GetMapping("/review/list")
	public List<CompaniesReviewDTO> allCompanyReview() {
        return rservice.showAllCompaniesReviews();
	}

//	@GetMapping("/company/review/{name}")
//	public List<CompaniesReviewDTO> allCompaniesReviews(@PathVariable("name") String companyName) {
//        return rservice.showByCompanyReview(companyName);
//	}


	@GetMapping("company/review/{companyname}")
	public List<ReviewDTO> allCompaniesReviews(@PathVariable("companyname") String companyName) {
		return rservice.findByCompanyReview(companyName);
    }
	
	@GetMapping("user/review/{userid}")
	public List<ReviewDTO> findReviewByUser(@PathVariable("userid") Long userid) {
		return rservice.findByApplicantReview(userid);
    }	
	
	@GetMapping("job/review/{jobtitle}")
	public List<ReviewDTO> findReviewByJob(@PathVariable("jobtitle") String jobTitle) {
		return rservice.findByJobReview(jobTitle);
    }	
	
	@GetMapping("job/company/{jobtitle}/{companyname}")
	public List<ReviewDTO> findReviewByJobandCompany(@PathVariable("jobtitle") String jobTitle,@PathVariable("companyname") String companyName) {
		List<Review> r = rrepo.findReviewsByCompanynameandJobTitle(companyName, jobTitle);
		return rservice.findReviewsByCompanynameandJobTitle(companyName, jobTitle);
    }	
		
	@PostMapping(path ="newreview")
    public Review saveReviewDTO(@RequestBody ReviewDTO rdto, Principal p) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(rdto.getReviewDate(),dtf);
        String username = p.getName();
        Applicant user = arepo.findApplicantByEmail(username).get(0);
        
        Review review = new Review(rdto.getReviewstars(),rdto.getReviewDescription(),
        		  crepo.findByCompanyName(rdto.getCompanyName().toString()).get(0),ld,
        		 jrepo.findByJobName(rdto.getJobTitle().toString()).get(0),
        		  user, "Approved");     
        Review fin = rservice.ValidateReview(review,rdto.getReviewDescription());
    return rservice.save(fin);
    }
	
	@DeleteMapping("deletereview/{reviewid}") 
	public ResponseEntity<Void> deleteReview(@PathVariable("reviewid") Long id) {
		try {
			rservice.delete(id); 
		  } catch (Exception e) { 
			  return ResponseEntity.notFound().build(); 
			  } 
		return ResponseEntity.noContent().build(); 
		}
	@GetMapping("list/reviews/{reviewid}/{reviewstatus}")
	public ResponseEntity<HttpStatus> updateReview(@PathVariable("reviewid") Long id, @PathVariable("reviewstatus") String status) {
	    
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


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
		List<Review> r = rrepo.findApprovedReviews();
		return generateLists(r);
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
		List<Review> r = crepo.findByCompanyReview(companyName);
		return generateLists(filterApprovedReviews(r));
    }
	
	@GetMapping("user/review/{userid}")
	public List<ReviewDTO> findReviewByUser(@PathVariable("userid") Long userid) {
		List<Review> r =arepo.findByApplicantReview(userid);
		return generateLists(filterApprovedReviews(r));
    }	
	
	@GetMapping("job/review/{jobtitle}")
	public List<ReviewDTO> findReviewByJob(@PathVariable("jobtitle") String jobTitle) {
		List<Review> r =jrepo.findByJobReview(jobTitle);
		return generateLists(filterApprovedReviews(r));
    }	
	
	@GetMapping("job/company/{jobtitle}/{companyname}")
	public List<ReviewDTO> findReviewByJobandCompany(@PathVariable("jobtitle") String jobTitle,@PathVariable("companyname") String companyName) {
		List<Review> r = rrepo.findReviewsByCompanynameandJobTitle(companyName, jobTitle);
		return generateLists(filterApprovedReviews(r));
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
        
        if(ValidateReview(rdto.getReviewDescription())){
        	review.setReviewStatus("Blocked");	
        	notifyAdmin();
        }
    return rservice.save(review);
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
	
	private List<ReviewDTO> generateLists(List<Review> r){
		List<ReviewDTO> rdto = new ArrayList<>(r.size());
		for(int i=0;i<r.size();i++) {
			rdto.add(new ReviewDTO());
			rdto.get(i).setCompanyName(r.get(i).getCompany().getName());
			rdto.get(i).setUserId(r.get(i).getApplicant().getId());
			  rdto.get(i).setReviewstars(r.get(i).getReviewstars());
			  rdto.get(i).setReviewDescription(r.get(i).getReviewDescription());
			  rdto.get(i).setReviewDate(r.get(i).getReviewDate().toString());
			  rdto.get(i).setJobTitle(r.get(i).getJob().getJobTitle());
			  rdto.get(i).setApplicantName(r.get(i).getApplicant().getFirstName().toString() + " " + r.get(i).getApplicant().getLastName().toString());
			  rdto.get(i).setReviewId(r.get(i).getId());
		}
		return rdto;
		
	}
	
//	@GetMapping("list/reviews/{reviewid}/{reviewstatus}/{applicantId}")
//	public ResponseEntity<HttpStatus> updateReview(@PathVariable("reviewid") Long id, @PathVariable("reviewstatus") String status
//			,@PathVariable("applicantId") String username) {
//	    
//		try {
//			rservice.updateReviewStatus(id, status,username);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		  } catch (Exception e) { 
//			  //return ResponseEntity.notFound().build(); 
//			  return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//			  } 
//		//return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
//		
//	}
	
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
	
	private Boolean ValidateReview(String desc){
		String [] arr = {"shit","terible", "crap", "worse", "stupid", "fuck"};
		String[] parts = desc.split(" ");
		
		for(String part: parts) {
			for(String a:arr) {
				if (part.equals(a)) {
					return true;}}}
		return false;
	}
	
	private void notifyAdmin() {
		
		final String username = "estherneohl269@gmail.com";
        final String password = "FILLYOURSELF";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testing@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("e0696698@u.nus.edu")
            );
            message.setSubject("Job Portal Review BLocked");
            message.setText("A Review has been blocked. Please login to update the status if need be. thank you");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException f) {
            f.printStackTrace();
        }	  
	}
	
	private List<Review> filterApprovedReviews(List<Review> r){
		
		List<Review> checked = new ArrayList<>();
		for(Review rr: r) {
			if(rr.getReviewStatus().equals("Approved")) {
				checked.add(rr);
			}
		}
		return checked;
	}
	

}

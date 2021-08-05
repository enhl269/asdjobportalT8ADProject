package sg.edu.iss.asdadt8.review;

import java.util.List;

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

import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Review;


@RestController
@RequestMapping("/api/review/")
public class ReviewController {
	
	@Autowired
	ReviewService rservice;
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	CompanyRepository crepo;
	
	
	@GetMapping("/list")
	public List<Review> allReview() {
        return rrepo.findAll();
    }
	
	@GetMapping("/company/{name}")
	public List<Company> allCompanies(@PathVariable("name") String companyName) {
        return crepo.findByCompanyName(companyName);
    }

	@GetMapping("/company/review/{name}")
	public List<Review> allCompaniesReviews(@PathVariable("name") String companyName) {
        return crepo.findByCompanyReview(companyName);
    }
	
	@GetMapping("hello")
    public List<Review> getallReview() {
      return rservice.getAllReview();
    }

	@PostMapping(path ="newreview", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
      rservice.save(review);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("review", "/api/review/" + Long.toString(review.getId()));
    return new ResponseEntity<>(review, httpHeaders, HttpStatus.CREATED);
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
    

}

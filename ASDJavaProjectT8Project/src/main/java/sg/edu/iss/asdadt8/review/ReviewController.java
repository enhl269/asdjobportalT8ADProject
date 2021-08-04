package sg.edu.iss.asdadt8.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.domain.Review;


@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	@Autowired
	private ReviewService rservice;
	
	@PostMapping("/newreview")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        rservice.save(review);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("review", "/api/review/" + Long.toString(review.getId()));
        return new ResponseEntity<>(review, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping({"/deletereview/{reviewid}"})
    public ResponseEntity<Review> deleteReview(@PathVariable("reviewid") Long id) {
        rservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}

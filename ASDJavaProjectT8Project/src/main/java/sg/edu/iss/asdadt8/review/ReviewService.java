package sg.edu.iss.asdadt8.review;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Review;


public interface ReviewService {
	
	Review save(Review review);
	
	void delete(Long id);
	
	List<Review> getAllReview();

}

package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Review;

public interface ReviewServiceWeb {
	
	List<Review> findAllReviews();
	
	List<Review> findApprovedReviews();
	
	List<Review> findBlockedReviews();
	
	void updateReviewStatus(Long id, String status);

}

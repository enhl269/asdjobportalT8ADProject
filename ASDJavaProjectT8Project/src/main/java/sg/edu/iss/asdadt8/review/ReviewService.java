package sg.edu.iss.asdadt8.review;

import sg.edu.iss.asdadt8.domain.Review;


public interface ReviewService {
	
	void save(Review review);
	
	void delete(Long id);

}

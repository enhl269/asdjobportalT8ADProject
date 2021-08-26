package sg.edu.iss.asdadt8.review;

import java.util.List;

import sg.edu.iss.asdadt8.DTOs.CompaniesReviewDTO;
import sg.edu.iss.asdadt8.DTOs.ReviewDTO;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Review;


public interface ReviewService {
	
	Review save(Review review);
	
	void delete(Long id);
	
	List<Review> getAllReview();

	List<CompaniesReviewDTO> showAllCompaniesReviews();

	List<CompaniesReviewDTO> showByCompanyReview(String companyName);

	void updateReviewStatus(Long id, String status);
	
	List<ReviewDTO> findApprovedReviews();
	
	List<ReviewDTO> findByCompanyReview(String companyName);
	
	List<ReviewDTO> findByApplicantReview(Long userid);
	List<ReviewDTO> findByJobReview(String jobTitle);
	
	List<ReviewDTO> findReviewsByCompanynameandJobTitle(String companyName, String jobTitle);
	
	Review ValidateReview(Review r, String s);
}

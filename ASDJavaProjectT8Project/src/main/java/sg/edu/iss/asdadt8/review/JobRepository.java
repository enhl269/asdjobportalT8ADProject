package sg.edu.iss.asdadt8.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;

public interface JobRepository extends JpaRepository<Job,Long>{
	
	@Query("SELECT j FROM Job j WHERE j.jobTitle =?1")
	List<Job> findByJobName(String JobName);
	
	@Query("SELECT j.reviews FROM Job j JOIN j.reviews r WHERE j.jobTitle =?1 "
			+ "AND r.reviewStatus = 'Approved'")
	List<Review> findByJobReview(String JobTitle);

}

package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.Review;

public interface ReviewRepositoryWeb extends JpaRepository<Review,Long>{
	
	@Query("SELECT r FROM Review r WHERE r.reviewStatus ='Blocked'")
	List<Review> findBlockedReviews();
	
	@Query("SELECT r FROM Review r WHERE r.reviewStatus ='Approved'")
	List<Review> findApprovedReviews();
	
	@Query("SELECT r FROM Review r WHERE r.reviewStatus ='Reported'")
	List<Review> findReportedReviews();
	
	@Modifying
	@Transactional
	@Query("update Review r set r.reviewStatus =:status where r.id=:id")
	public void saveStatus(long id,String status);

}

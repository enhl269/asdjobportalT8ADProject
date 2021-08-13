package sg.edu.iss.asdadt8.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.asdadt8.domain.Review;


public interface ReviewRepository extends JpaRepository<Review,Long>{

	@Query("SELECT r FROM Review r WHERE r.id =?1")
	Review findByReviewId(Long id);
	
	@Query("SELECT r FROM Review r JOIN r.company c "
			+ "JOIN c.job j "
			+ "where c.name=:cname "
			+ "AND j.jobTitle=:jobTitle "
			+ "AND r.reviewStatus= 'Approved'")
	public List<Review> findReviewsByCompanynameandJobTitle(@Param("cname") String cname,@Param("jobTitle") String jobTitle);
	
	@Query("SELECT r FROM Review r JOIN r.job j "
			+ "where j.jobTitle=:jobTitle "
			+ "AND r.reviewStatus= 'Approved'")
	public List<Review> findReviewsByJobTitle(@Param("jobTitle") String jobTitle);
	
	@Query("SELECT r FROM Review r JOIN r.applicant a "
			+ "where a.id =?1 "
			+ "AND r.reviewStatus= 'Approved'")
	public List<Review> findReviewsByApplicant(@Param("id") Long id);
	
	@Query("SELECT r FROM Review r JOIN r.company c "
			+ "where c.name=:cname "
			+ "AND r.reviewStatus= 'Approved'")
	public List<Review> findReviewsByCompanyname(@Param("cname") String cname);
	
	@Query("SELECT r FROM Review r "
			+ "where r.reviewStatus= 'Approved'")
	public List<Review> findApprovedReviews();
	
	
}

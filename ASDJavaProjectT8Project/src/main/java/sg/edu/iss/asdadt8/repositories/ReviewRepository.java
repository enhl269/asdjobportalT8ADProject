package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.Review;


public interface ReviewRepository extends JpaRepository<Review,Long>{

	@Query("SELECT r FROM Review r WHERE r.id =?1")
	Review findByReviewId(Long id);
	
	@Query("SELECT r FROM Review r JOIN r.company c "
			+ "JOIN c.job j "
			+ "where c.name=:cname "
			+ "AND j.jobTitle=:jobTitle")
	public List<Review> findReviewsByCompanynameandJobTitle(@Param("cname") String cname,@Param("jobTitle") String jobTitle);
	
	@Modifying
	@Transactional
	@Query("update Review r set r.reviewStatus =:status where r.id=:id")
	public void saveStatus(long id,String status);
}

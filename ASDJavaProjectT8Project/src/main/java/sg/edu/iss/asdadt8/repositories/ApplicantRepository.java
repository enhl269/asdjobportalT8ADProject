package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Review;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public List<Applicant> findApplicantByID(Long id);
	
	@Query("SELECT a FROM Applicant a WHERE a.email = :email")
	public List<Applicant> findApplicantByEmail(@Param("email") String email);
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public Applicant findOneApplicantByID(Long id);
	
	@Query("SELECT a.reviews FROM Applicant a WHERE a.id =?1")
	List<Review> findByApplicantReview(Long id);

	
	@Query("SELECT a FROM Applicant a WHERE a.userStatus = 'Approve'")
	List<Applicant> findApplicantByApproveStatus();
	
	@Query("SELECT a FROM Applicant a WHERE a.userStatus = 'Block'")
	List<Applicant> findApplicantByBlockStatus();
	
	@Query("SELECT a FROM Applicant a")
	List<Applicant> findAllApplicant();
	
	Applicant findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("update Applicant a set a.userStatus =:status where a.id=:id")
	public void updateUserStatus(long id,String status);
	
	@Query("SELECT r FROM Applicant a JOIN a.reviews r WHERE r.reviewStatus =:status AND a.email=:username")
	public List<Review> findReviewByApplicantEmail(String status, String username);
	

}
package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Review;

public interface ApplicantWebRepository extends JpaRepository<Applicant,Long>{
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public Applicant findApplicantByID(Long id);
	
	@Query("SELECT a.reviews FROM Applicant a WHERE a.id =?1")
	List<Review> findByApplicantReview(Long id);
	
	@Query("SELECT a FROM Applicant a WHERE a.userStatus = 'Approve'")
	List<Applicant> findApplicantByApproveStatus();
	
	@Query("SELECT a FROM Applicant a WHERE a.userStatus = 'Blocked'")
	List<Applicant> findApplicantByBlockStatus();
	
	@Query("SELECT a FROM Applicant a")
	List<Applicant> findAllApplicant();
	
	@Query("SELECT r FROM Applicant a JOIN a.reviews r WHERE r.reviewStatus =:status AND a.email=:username")
	public List<Review> findReviewByApplicantEmail(String status, String username);
	
	@Modifying
	@Transactional
	@Query("update Applicant a set a.userStatus =:status where a.id=:id")
	public void updateUserStatus(long id,String status);
	
	Applicant findByEmail(String email);

	@Query("SELECT a FROM Applicant a WHERE a.userStatus = 'Reported'")
	public List<Applicant> findApplicantByReportStatus();
	
	

}

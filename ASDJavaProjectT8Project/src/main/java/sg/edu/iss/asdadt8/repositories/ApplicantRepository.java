package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Review;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public List<Applicant> findApplicantByID(Long id);
	
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

}
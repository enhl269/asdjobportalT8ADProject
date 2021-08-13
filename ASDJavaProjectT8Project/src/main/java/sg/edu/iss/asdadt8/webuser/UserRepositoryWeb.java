package sg.edu.iss.asdadt8.webuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.asdadt8.domain.Applicant;

public interface UserRepositoryWeb extends JpaRepository<Applicant,Long>{
	
	@Query("SELECT a FROM Applicant a")
	public Applicant getApplicant();
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public Applicant findApplicantByID(Long id);

}

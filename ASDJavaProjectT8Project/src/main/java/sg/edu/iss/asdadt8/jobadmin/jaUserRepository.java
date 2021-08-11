package sg.edu.iss.asdadt8.jobadmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.asdadt8.domain.Applicant;

public interface jaUserRepository extends JpaRepository<Applicant,Long>{
	@Query("SELECT a FROM Applicant a")
	public Applicant getApplicant();
	
	@Query("SELECT a FROM Applicant a WHERE a.id = ?1")
	public Applicant findApplicantByID(Long id);

}

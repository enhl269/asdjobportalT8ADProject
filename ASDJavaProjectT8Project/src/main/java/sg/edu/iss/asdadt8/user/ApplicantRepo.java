package sg.edu.iss.asdadt8.user;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.User;

public interface ApplicantRepo extends JpaRepository<Applicant, Long> {
	Applicant findByEmail(String email);
}

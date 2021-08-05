package sg.edu.iss.asdadt8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}

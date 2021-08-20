package sg.edu.iss.asdadt8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.asdadt8.domain.ResumeFile;

@Repository
public interface ResumeFileRepository extends JpaRepository<ResumeFile, String>{

}
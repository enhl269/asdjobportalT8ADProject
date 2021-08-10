package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import sg.edu.iss.asdadt8.domain.ViewedJobs;

public interface jaViewedJobsRepository extends JpaRepository<ViewedJobs,Long>{
	
	@Query("SELECT j FROM ViewedJobs j where j.applicant.id=:id")
	public List<ViewedJobs> findAll(@Param("id") Long id);

}

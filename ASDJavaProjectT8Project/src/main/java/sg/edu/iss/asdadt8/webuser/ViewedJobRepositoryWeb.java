package sg.edu.iss.asdadt8.webuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

public interface ViewedJobRepositoryWeb extends JpaRepository<ViewedJobs,Long>{
	
	@Query("SELECT v FROM ViewedJobs v where v.applicant.id=:applicant")
	public List<ViewedJobs> findViewedJobsByUserID(@Param("applicant") Long applicant);
	
	@Query("SELECT j FROM ViewedJobs j where j.applicant.id=:id")
	public List<ViewedJobs> findAll(@Param("id") Long id);
	
	@Query("SELECT v FROM ViewedJobs v where v.applicant.email=:applicant")
	public List<ViewedJobs> findViewedJobsByUserEmail(@Param("applicant") String applicant);
	

}

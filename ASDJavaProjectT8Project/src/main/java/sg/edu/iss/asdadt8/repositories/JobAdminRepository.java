package sg.edu.iss.asdadt8.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

public interface JobAdminRepository extends JpaRepository<Job,Long>{
	@Query("SELECT j FROM Job j where j.id=:id")
	public Job findsJobById(@Param("id") Long id);

	@Modifying
	@Query(value = "insert into bookmarked_jobs(job_id, applicant_id, bookmark_date) values(:job, :applicant, :date)",nativeQuery = true)
	@Transactional
	public void saveBookMark(@Param("job") Long job_id,@Param("applicant") Long applicant_id,@Param("date")LocalDate localDate);
	
	@Modifying
	@Query(value = "insert into viewed_jobs(job_id, applicant_id, date_viewed) values(:job, :applicant, :date)",nativeQuery = true)
	@Transactional
	public void saveApplyJob(@Param("job") Long job_id,@Param("applicant") Long applicant_id,@Param("date")LocalDate localDate);
	
	@Query("SELECT b FROM BookmarkedJobs b where b.applicant.id=:applicant")
	public List<BookmarkedJobs> findBookmarkByUserID(@Param("applicant") Long applicant);
	
	@Query("SELECT v FROM ViewedJobs v where v.applicant.id=:applicant")
	public List<ViewedJobs> findViewedJobsByUserID(@Param("applicant") Long applicant);
}

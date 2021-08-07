package sg.edu.iss.asdadt8.jobadmin;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.asdadt8.domain.Job;

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
}

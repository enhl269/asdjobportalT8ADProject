package sg.edu.iss.asdadt8.webuser;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

public interface JobRepositoryWeb extends JpaRepository<Job,Long>{
	
	@Query("SELECT j From Job j WHERE j.jobTitle LIKE %:search% OR j.jobDescription LIKE %:search% ")
	List<Job>findJobByNameOrDesc(@Param("search") String search);

	//type here mean full time or part-time
	@Query("SELECT j From Job j WHERE j.jobTitle = :title AND j.jobStarRating > :jobStarRating AND j.autismLevel = :autismLvl")
	List<Job>filterJobs(@Param("title")String title,@Param("jobStarRating")float jobStarRating,@Param("autismLvl")int autismLvl);

	@Query("SELECT j FROM Job j WHERE j.jobIndustry =:industry")
	List<Job>categorizeJobsList(@Param("industry")String industry);
	
	@Query("SELECT distinct j.jobIndustry,count(*) FROM Job j group by j.jobIndustry")
	List<String>categorizeJobs();
	
	@Modifying
	@Query(value = "insert into bookmarked_jobs(job_id, applicant_id, bookmark_date) values(:job, :applicant, :date)",nativeQuery = true)
	@Transactional
	public void saveBookMark(@Param("job") Long job_id,@Param("applicant") Long applicant_id,@Param("date")LocalDate localDate);
	
	@Modifying
	@Query(value = "insert into viewed_jobs(job_id, applicant_id, date_viewed) values(:job, :applicant, :date)",nativeQuery = true)
	@Transactional
	public void saveApplyJob(@Param("job") Long job_id,@Param("applicant") Long applicant_id,@Param("date")LocalDate localDate);
	

	


}

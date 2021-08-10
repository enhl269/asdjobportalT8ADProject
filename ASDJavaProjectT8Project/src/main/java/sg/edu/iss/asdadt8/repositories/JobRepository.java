package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;

public interface JobRepository extends JpaRepository<Job,Long>{
	

	@Query("SELECT j FROM Job j WHERE j.jobTitle =?1")
	List<Job> findByJobName(String JobName);
	
	@Query("SELECT j.reviews FROM Job j WHERE j.jobTitle =?1")
	List<Review> findByJobReview(String JobTitle);
	
	@Query("SELECT j From Job j WHERE j.jobTitle LIKE %:search% OR j.jobDescription LIKE %:search% ")
	List<Job>findJobByNameOrDesc(@Param("search") String search);

	//type here mean full time or part-time
	@Query("SELECT j From Job j WHERE j.jobTitle = :title AND j.jobStarRating > :jobStarRating AND j.autismLevel = :autismLvl")
	List<Job>filterJobs(@Param("title")String title,@Param("jobStarRating")float jobStarRating,@Param("autismLvl")int autismLvl);

	@Query("SELECT j FROM Job j WHERE j.jobIndustry =:industry")
	List<Job>categorizeJobsList(@Param("industry")String industry);
	
	@Query("SELECT distinct j.jobIndustry,count(*) FROM Job j group by j.jobIndustry")
	List<String>categorizeJobs();

}

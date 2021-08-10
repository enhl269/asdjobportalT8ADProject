package sg.edu.iss.asdadt8.job;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Job;

public interface JobService {

	Job findJobById(Long Id);
	List<String> jobIndustryList();
	List<Job>showallJobs();
	List<Job>findJobs(String searchKey);
	List<Job>categorizeJobs(String cateogirzeId);

	List<Job>filterJobs(String title,float jobStarRating, int autismLvl);
}

package sg.edu.iss.asdadt8.job;

import java.util.List;

import sg.edu.iss.asdadt8.DTOs.BookmarkedJobsDTO;
import sg.edu.iss.asdadt8.DTOs.JobAdminDTO;
import sg.edu.iss.asdadt8.DTOs.ResponseMessage;
import sg.edu.iss.asdadt8.DTOs.ViewedJobsDTO;
import sg.edu.iss.asdadt8.domain.Job;

public interface JobService {

	
	// job list
	List<String> jobIndustryList();
	List<Job>showallJobs();
	List<Job>findJobs(String searchKey);
	List<Job>categorizeJobs(String cateogirzeId);

	List<Job>filterJobs(String title,float jobStarRating, int autismLvl);
	
	
	//job detail
	List<Job> getAllJobs();
	
	void saveBookMark(long id);
	
	JobAdminDTO findJobById(Long Id);
    
	ResponseMessage applyJobUrl(long id);
	JobAdminDTO applyJobEmail(long id);
	JobAdminDTO sharejoburl(long id);
	
	List<BookmarkedJobsDTO> findBookmarkByUserID(long applicant_id);
	List<ViewedJobsDTO> findViewedJobsByUserID(long applicant_id);
}

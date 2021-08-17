package sg.edu.iss.asdadt8.webuser;

import java.util.List;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

public interface JobServiceWeb {
	
	Job findJobById(Long Id);
	List<String> jobIndustryList();
	List<Job>showallJobs();
	List<Job>findJobs(String searchKey);
	List<Job>categorizeJobs(String cateogirzeId);
	List<Job>filterJobs(String title,float jobStarRating, int autismLvl);
	
	void saveBookMark(long id);
	String applyJobUrl(long id);
	String applyJobEmail(long id);
	List<ViewedJobs> findViewedJobsByApplicantId(long id);
	List<BookmarkedJobs> findBookmarkedJobsByApplicantId(long id);
}

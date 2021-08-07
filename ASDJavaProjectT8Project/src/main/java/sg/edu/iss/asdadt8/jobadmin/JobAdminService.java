package sg.edu.iss.asdadt8.jobadmin;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Job;

public interface JobAdminService {
	
	void saveBookMark(long id);
	
    Job findJobById(Long Id);
    
	List<Job> getAllJobs();
	
	String applyJobUrl(long id);
	String applyJobEmail(long id);
	
	//List<BookmarkedJobs> findBookmarkById(Long Id);
}

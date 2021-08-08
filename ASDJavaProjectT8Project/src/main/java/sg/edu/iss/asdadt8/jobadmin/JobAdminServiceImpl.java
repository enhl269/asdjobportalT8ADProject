package sg.edu.iss.asdadt8.jobadmin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

@Service
public class JobAdminServiceImpl implements JobAdminService{

	@Autowired
	JobAdminRepository jrepo;
	
	@Autowired
	jaBookmarkRepository brepo;
	
	@Autowired
	jaUserRepository urepo;
	
	@Autowired
	jaViewedJobsRepository vrepo;
	
	@Override
	public List<Job> getAllJobs() {
		return jrepo.findAll();
	}
	
	//find job by job id with DTO
	@Override
	public  JobAdminDTO findJobById(Long Id) {
		Job job = jrepo.findsJobById(Id);
		JobAdminDTO jobadmin = new JobAdminDTO(job.getId(), job.getJobTitle(), job.getJobIndustry(), job.getJobDescription(), job.getAutismLevel(), 
				job.getJobStarRating(), job.getJobPositionURL(), job.getCompany().getName());
				
		return jobadmin;
	}
	
	//find all book mark by user id 
	//for list all book mark list 
	//@Override
	//public List<BookmarkedJobs> findBookmarkById(Long Id) {
	//	return brepo.findBookmarkById(Id);
	//}
	
	@Override
	public void saveBookMark(long id) {
		//get by id 
		Job job_details = jrepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByID((long)1);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
		
		//get all 
		List<Job> allJobs = getAllJobs();
		List<BookmarkedJobs> allBmj = brepo.findAll();
		
		//checker- true when no duplicate book mark
		boolean check = true;
		
		//loop all book marks to check for duplicate
		for (int i = 0; i < allBmj.size() ; i++){
			if( user_session.getId() == allBmj.get(i).getApplicant().getId() && job_details.getId() == allBmj.get(i).getJob().getId() ) {
				check = false;
			}
		}
		
		if (check == true){
			jrepo.saveBookMark(job_details.getId(), user_session.getId(),localdate);
		}
	}
	
	public String applyJobUrl(long id) {
		//get by id 
		Job job_details = jrepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByID((long)1);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
		
		//get all 
		List<Job> allJobs = getAllJobs();
		//get all applied jobs by applicant ID
		List<ViewedJobs> allApplied = vrepo.findAll((long)1); //need to update to get user for session.
		boolean check = true;
		
		//check for duplicate in viewedjobs 
		for (int i = 0; i < allApplied.size() ; i++){
			if( user_session.getId() == allApplied.get(i).getApplicant().getId() && job_details.getId() == allApplied.get(i).getJob().getId() ) {
				check = false;
			}
		}
		
		//save to ViewedJobs (apply)
		if (check == true){
			jrepo.saveApplyJob(job_details.getId(), user_session.getId(),localdate);
		}
		
		//return string company url address
		return job_details.getJobPositionURL();
	}
	
	
	public String applyJobEmail(long id) {
		//get by id 
		Job job_details = jrepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByID((long)1);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
				
		//get all 
		List<Job> allJobs = getAllJobs();
		//get all applied jobs by applicant ID
		List<ViewedJobs> allApplied = vrepo.findAll((long)1); //need to update to get user for session.
		boolean check = true;
				
		//check for duplicate in viewedjobs 
		for (int i = 0; i < allApplied.size() ; i++){
			if( user_session.getId() == allApplied.get(i).getApplicant().getId() && job_details.getId() == allApplied.get(i).getJob().getId() ) {
				check = false;
			}
		}
				
		//save to ViewedJobs (apply)
		if (check == true){
			jrepo.saveApplyJob(job_details.getId(), user_session.getId(),localdate);
		}
				
		//return string company HR email
		return job_details.getCompany().getHrEmail();
	}
	
	public List<BookmarkedJobsDTO> findBookmarkByUserID(long applicant_id){
		List<BookmarkedJobs> bookmarkedjobs_list = jrepo.findBookmarkByUserID(applicant_id);
		
		//BookmarkedJobsDTO bookmarkedjobs_temp = new BookmarkedJobsDTO();
		List<BookmarkedJobsDTO> bookmarkedjobs_transfer = new ArrayList<>();
		
		for (BookmarkedJobs a: bookmarkedjobs_list) {
			//long id, String jobtitle, String companyname, LocalDate bookmarkDate
			bookmarkedjobs_transfer.add(new BookmarkedJobsDTO(a.getId(), a.getJob().getJobTitle(), 
					a.getJob().getCompany().getName(), a.getBookmarkDate()));
		}
			
		return bookmarkedjobs_transfer;
	}
	
	public List<ViewedJobsDTO> findViewedJobsByUserID(long applicant_id){
		List<ViewedJobs> viewedjobs_list = jrepo.findViewedJobsByUserID(applicant_id);
		
		//BookmarkedJobsDTO bookmarkedjobs_temp = new BookmarkedJobsDTO();
		List<ViewedJobsDTO> viewedjobs_transfer = new ArrayList<>();
		
		for (ViewedJobs a: viewedjobs_list) {
			//long id, String jobtitle, String companyname, LocalDate bookmarkDate
			viewedjobs_transfer.add(new ViewedJobsDTO(a.getId(), a.getJob().getJobTitle(), 
					a.getJob().getCompany().getName(), a.getDateViewed()));
		}
			
		return viewedjobs_transfer;
	}
	
}

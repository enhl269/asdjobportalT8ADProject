package sg.edu.iss.asdadt8.job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.DTOs.BookmarkedJobsDTO;
import sg.edu.iss.asdadt8.DTOs.JobAdminDTO;
import sg.edu.iss.asdadt8.DTOs.ResponseMessage;
import sg.edu.iss.asdadt8.DTOs.ViewedJobsDTO;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;
import sg.edu.iss.asdadt8.repositories.JobAdminRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;
import sg.edu.iss.asdadt8.repositories.jaBookmarkRepository;
import sg.edu.iss.asdadt8.repositories.jaUserRepository;
import sg.edu.iss.asdadt8.repositories.jaViewedJobsRepository;

@Service
public class JobServiceImpl implements JobService {


	@Autowired
	JobRepository jrepo;
	

	@Autowired
	JobAdminRepository jobAdminRepo;
	
	@Autowired
	jaBookmarkRepository brepo;
	
	@Autowired
	jaUserRepository urepo;
	
	@Autowired
	jaViewedJobsRepository vrepo;
	
	// Job List

	@Override
	public List<Job> showallJobs() {
		// TODO Auto-generated method stub
		return jrepo.findAll();
	}

	@Override
	public List<Job> findJobs(String searchKey) {
		// TODO Auto-generated method stub
		return jrepo.findJobByNameOrDesc(searchKey);
	}

	@Override
	public List<Job> filterJobs(String title,float jobStarRating, int autismLvl) {
		// TODO Auto-generated method stub
		return jrepo.filterJobs(title, jobStarRating, autismLvl);
	}



	@Override
	public List<Job> categorizeJobs(String cateogirzeId) {
		// TODO Auto-generated method stub
		return jrepo.categorizeJobsList(cateogirzeId);
	}

	@Override
	public List<String> jobIndustryList() {
		// TODO Auto-generated method stub
		return jrepo.categorizeJobs();
	}
	
	
	// Job Detail
	

	
	@Override
	public List<Job> getAllJobs() {
		return jobAdminRepo.findAll();
	}
	
	//find job by job id with DTO
	@Override
	public  JobAdminDTO findJobById(Long Id) {
		Job job = jobAdminRepo.findsJobById(Id);
		JobAdminDTO jobadmin = new JobAdminDTO(job.getId(), job.getJobTitle(), job.getJobIndustry(), job.getJobqualification(),job.getJobDescription(), job.getAutismLevel(), 
				job.getJobStarRating(), job.getJobPositionURL(), job.getCompany().getName(),job.getCompany().getHrEmail());
				
		return jobadmin;
	}
	
	//find all book mark by user id 
	//for list all book mark list 
	//@Override
	//public List<BookmarkedJobs> findBookmarkById(Long Id) {
	//	return brepo.findBookmarkById(Id);
	//}
	
	@Override
	public void saveBookMark(long id, String username) {
		//get by id 
		Job job_details = jobAdminRepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
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
			jobAdminRepo.saveBookMark(job_details.getId(), user_session.getId(),localdate);
		}
	}
	
	public ResponseMessage applyJobUrl(long id, String username) {
		//get by id 
		Job job_details = jobAdminRepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
		
		//get all 
		List<Job> allJobs = getAllJobs();
		//get all applied jobs by applicant ID
		List<ViewedJobs> allApplied = vrepo.findAll(user_session.getId()); //need to update to get user for session.
		boolean check = true;
		
		//check for duplicate in viewedjobs 
		for (int i = 0; i < allApplied.size() ; i++){
			if( user_session.getId() == allApplied.get(i).getApplicant().getId() && job_details.getId() == allApplied.get(i).getJob().getId() ) {
				check = false;
			}
		}
		
		//save to ViewedJobs (apply)
		if (check == true){
			jobAdminRepo.saveApplyJob(job_details.getId(), user_session.getId(),localdate);
		}
		
		//return string company url address
		ResponseMessage message = new ResponseMessage();
		message.setMessage(job_details.getJobPositionURL());
		return message;
	}
	
	
	public JobAdminDTO applyJobEmail(long id, String username) {
		//get by id 
		Job job = jobAdminRepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
				
		//get all 
		List<Job> allJobs = getAllJobs();
		//get all applied jobs by applicant ID
		List<ViewedJobs> allApplied = vrepo.findAll(user_session.getId()); //need to update to get user for session.
		boolean check = true;
				
		//check for duplicate in viewedjobs 
		for (int i = 0; i < allApplied.size() ; i++){
			if( user_session.getId() == allApplied.get(i).getApplicant().getId() && job.getId() == allApplied.get(i).getJob().getId() ) {
				check = false;
			}
		}
				
		//save to ViewedJobs (apply)
		if (check == true){
			jobAdminRepo.saveApplyJob(job.getId(), user_session.getId(),localdate);
		}
				
		//return string company HR email
		JobAdminDTO jobadmin = new JobAdminDTO(job.getId(), job.getJobTitle(), job.getJobIndustry(), job.getJobqualification(),job.getJobDescription(), job.getAutismLevel(), 
				job.getJobStarRating(), job.getJobPositionURL(), job.getCompany().getName() , job.getCompany().getHrEmail());
		return jobadmin;
	}
	
	public JobAdminDTO sharejoburl(long id, String username) {
		//get by id 
		Job job = jobAdminRepo.findsJobById(id);
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
		
		JobAdminDTO jobadmin = new JobAdminDTO(job.getId(), job.getJobTitle(), job.getJobIndustry(), job.getJobqualification(),job.getJobDescription(), job.getAutismLevel(), 
				job.getJobStarRating(), job.getJobPositionURL(), job.getCompany().getName(), job.getCompany().getHrEmail());				

		return jobadmin;
	}
	
	public List<BookmarkedJobsDTO> findBookmarkByUserID(long applicant_id){
		List<BookmarkedJobs> bookmarkedjobs_list = jobAdminRepo.findBookmarkByUserID(applicant_id);
		
		//BookmarkedJobsDTO bookmarkedjobs_temp = new BookmarkedJobsDTO();
		List<BookmarkedJobsDTO> bookmarkedjobs_transfer = new ArrayList<>();
		
		for (BookmarkedJobs a: bookmarkedjobs_list) {
			//long id, String jobtitle, String companyname, LocalDate bookmarkDate
			bookmarkedjobs_transfer.add(new BookmarkedJobsDTO(a.getId(), a.getJob().getJobTitle(), 
					a.getJob().getCompany().getName(), a.getBookmarkDate().toString()));
		}
			
		return bookmarkedjobs_transfer;
	}
	
	public List<ViewedJobsDTO> findViewedJobsByUserID(long applicant_id){
		List<ViewedJobs> viewedjobs_list = jobAdminRepo.findViewedJobsByUserID(applicant_id);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//BookmarkedJobsDTO bookmarkedjobs_temp = new BookmarkedJobsDTO();
		List<ViewedJobsDTO> viewedjobs_transfer = new ArrayList<>();
		
		for (ViewedJobs a: viewedjobs_list) {
			//long id, String jobtitle, String companyname, string bookmarkDate
			viewedjobs_transfer.add(new ViewedJobsDTO(a.getId(), a.getJob().getJobTitle(), 
					a.getJob().getCompany().getName(), a.getDateViewed().format(dtf)));
		}
			
		return viewedjobs_transfer;
	}
	
	public long findApplicantId(String username) {
		return urepo.findApplicantByEmail(username).getId();
	}
}

package sg.edu.iss.asdadt8.jobadmin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.DTOs.BookmarkedJobsDTO;
import sg.edu.iss.asdadt8.DTOs.JobAdminDTO;
import sg.edu.iss.asdadt8.DTOs.ResponseMessage;
import sg.edu.iss.asdadt8.DTOs.ViewedJobsDTO;
import sg.edu.iss.asdadt8.domain.BookmarkedJobs;

@RestController
@RequestMapping("/api/jobadmin/")
public class JobAdminController {
	
	//hello
	@Autowired
	JobAdminService jservice;
	
	@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
	public JobAdminDTO ListJob(Model model, @PathVariable("id") Long Id) {
		JobAdminDTO jobdetails_obj = jservice.findJobById(Id);
		//model.addAttribute("jobdetails_obj",jobdetails_obj);
		// "jobdetails";
		return jobdetails_obj;
	}
	
	@RequestMapping(value= "bookmark/{id}" , method = RequestMethod.POST)
	public void SaveBookmark(@PathVariable("id") Long Id) {
		jservice.saveBookMark(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "applyjoburl/{id}" , method = RequestMethod.POST)
	//job ID
	public ResponseMessage ApplyJobUrl(@PathVariable("id") Long Id){
		return jservice.applyJobUrl(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "applyjobemail/{id}" , method = RequestMethod.POST)
	//job ID
	public JobAdminDTO ApplyJobEmail(@PathVariable("id") Long Id){
		return jservice.applyJobEmail(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "shareurl/{id}" , method = RequestMethod.POST)
	//job ID
	public JobAdminDTO ShareJobUrl(@PathVariable("id") Long Id){
		return jservice.sharejoburl(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "details/bookmark/list",method = RequestMethod.GET)
    public List<BookmarkedJobsDTO> ListBookmarkJobs(Model model){
		Long Id = (long) 1; //PENDING 
	    List<BookmarkedJobsDTO> bookmarkedjobs = jservice.findBookmarkByUserID(Id);
		return bookmarkedjobs;
	}
	
	@RequestMapping(value= "details/viewed/list",method = RequestMethod.GET)
    public List<ViewedJobsDTO> ListViewedJobs(Model model){
		Long Id = (long) 1; //PENDING 
	    List<ViewedJobsDTO> viewedjobs = jservice.findViewedJobsByUserID(Id);
		return viewedjobs;
	}
	
	
}

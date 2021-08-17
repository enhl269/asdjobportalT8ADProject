package sg.edu.iss.asdadt8.job;

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
import sg.edu.iss.asdadt8.domain.Job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.domain.Job;


	@RestController
	@RequestMapping("/api/job/")
	public class JobController {
		

		
		@Autowired
		JobServiceImpl jservice;

		
		
		//Job List 
		
		@GetMapping("list")
		public List<Job>viewJobs(){
			return jservice.showallJobs();
		}
		
		@GetMapping("search/{key}")
		public List<Job>searchJobs(@PathVariable("key")String key){
			return jservice.findJobs(key);
		}
	
		@GetMapping("categorize/{cateogirzeId}")
		public List<Job>groupbyJobs(@PathVariable("cateogirzeId")String cateogirzeId){
			return jservice.categorizeJobs(cateogirzeId);
		}
		//need to modify
		@GetMapping("filter/{title}/{jobStarRating}/{autismLvl}")
		public List<Job>filterJobs(@PathVariable("title")String title,@PathVariable("jobStarRating")float jobStarRating,@PathVariable("autismLvl")int autismLvl){
			return jservice.filterJobs(title,jobStarRating,autismLvl);
		}
		@GetMapping("category")
		public List<String>categoryList(){
			return jservice.jobIndustryList();
		}
		

		
		//Job Detail
		
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

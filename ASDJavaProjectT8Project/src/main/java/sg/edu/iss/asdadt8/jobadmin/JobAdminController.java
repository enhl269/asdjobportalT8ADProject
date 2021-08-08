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

import sg.edu.iss.asdadt8.domain.Job;

@RestController
@RequestMapping("/api/jobadmin/")
public class JobAdminController {

	@Autowired
	JobAdminService jservice;
	
	@GetMapping("list")
    public List<Job> getallJobs() {
      return jservice.getAllJobs();
    }
	
	@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
	public JobAdminDTO ListJob(Model model, @PathVariable("id") Long Id) {
		JobAdminDTO jobdetails_obj = jservice.findJobById(Id);
		
		//model.addAttribute("jobdetails_obj",jobdetails_obj);
		// "jobdetails";
		return jobdetails_obj;
	}
	
	@RequestMapping(value= "details/bookmark/{id}" , method = RequestMethod.POST)
	public void SaveBookmark(@PathVariable("id") Long Id) {
		
		jservice.saveBookMark(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "details/applyjoburl/{id}" , method = RequestMethod.POST)
	//job ID
	public String ApplyJobUrl(@PathVariable("id") Long Id){
		
		return jservice.applyJobUrl(Id);
		//return "redirect:/details/" + Id;
	}
	
	@RequestMapping(value= "details/applyjobemail/{id}" , method = RequestMethod.POST)
	//job ID
	public String ApplyJobEmail(@PathVariable("id") Long Id){
		
		return jservice.applyJobEmail(Id);
		//return "redirect:/details/" + Id;
	}
	
	
	
}

package sg.edu.iss.asdadt8.webuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/webuser/")
public class WebUserJobController {
	
	@Autowired
	JobServiceImplWeb jservice;
	
	@GetMapping("job/list")
	public List<WebUserJobDTO>viewJobs(){
		List<Job> j =jservice.showallJobs();
		return generateJobLists(j);
	}
	
	@GetMapping("job/list/search/{key}")
	public List<WebUserJobDTO>searchJobs(@PathVariable("key")String key){
		List<Job> j =jservice.findJobs(key);
		return generateJobLists(j);
	}
	@GetMapping("job/detail/{jobId}")
	public WebUserJobDTO showJobDetail(@PathVariable("jobId")Long jobId) {
		Job j = jservice.findJobById(jobId);
		WebUserJobDTO fin = new WebUserJobDTO(j.getJobTitle(),j.getJobIndustry(),j.getJobqualification(),
				j.getJobDescription(),j.getAutismLevel(),j.getJobStarRating(),j.getJobPositionURL(),
				j.getId(),j.getCompany().getId(),j.getCompany().getName(),j.getCompany().getStarRating());
		return fin;
	}
	@GetMapping("job/list/categorize/{cateogirzeId}")
	public List<WebUserJobDTO>groupbyJobs(@PathVariable("cateogirzeId")String cateogirzeId){
		List<Job> j =jservice.categorizeJobs(cateogirzeId);
		return generateJobLists(j);
	}

	@GetMapping("job/list/filter/{title}/{jobStarRating}/{autismLvl}")
	public List<WebUserJobDTO>filterJobs(@PathVariable("title")String title,@PathVariable("jobStarRating")float jobStarRating,@PathVariable("autismLvl")int autismLvl){
		List<Job> j =jservice.filterJobs(title,jobStarRating,autismLvl);
		return generateJobLists(j);
	}
	@GetMapping("job/category")
	public Map<String,String>categoryList(){
		
		List<String> s = jservice.jobIndustryList();
		Map<String, String> map = new HashMap<>();
	      for(String y: s){
	          String[] a = y.split(",");
	          map.put(a[0],a[1]);
	      }
		return map;
	}
	
	@PostMapping("bookmarkjob/{id}")
	public void SaveBookmark(@PathVariable("id") Long Id) {
		jservice.saveBookMark(Id);
	}
	
	@PostMapping("applyjoburl/{id}")
	public String ApplyJobUrl(@PathVariable("id") Long Id){
		return jservice.applyJobUrl(Id);
	}
	
	@PostMapping("applyjobemail/{id}")
	public String ApplyJobEmail(@PathVariable("id") Long Id){
		return jservice.applyJobEmail(Id);
	}
	
	@GetMapping("bookmarkedjobs/list")
    public List<WebUserJobDTO> ListBookmarkJobs(){
		Long Id = (long) 1; //PENDING 
		List<BookmarkedJobs> j =jservice.findBookmarkedJobsByApplicantId(Id);
		return generateBookmarkJobLists(j);
	}
	
	@GetMapping("viewedjobs/list")
    public List<WebUserJobDTO> ListViewedJobs(){
		Long Id = (long) 1; //PENDING 
		List<ViewedJobs> j =jservice.findViewedJobsByApplicantId(Id);
		return generateViewJobLists(j);
	}
	
	private List<WebUserJobDTO> generateJobLists(List<Job> r){
		List<WebUserJobDTO> rdto = new ArrayList<>(r.size());
		for(int i=0;i<r.size();i++) {
			rdto.add(new WebUserJobDTO());
			rdto.get(i).setJobTitle(r.get(i).getJobTitle());
			rdto.get(i).setJobIndustry(r.get(i).getJobIndustry());
			rdto.get(i).setJobqualification(r.get(i).getJobqualification());
			rdto.get(i).setJobDescription(r.get(i).getJobDescription());
			rdto.get(i).setAutismLevel(r.get(i).getAutismLevel());
			rdto.get(i).setJobStarRating(r.get(i).getJobStarRating());
			rdto.get(i).setJobPositionURL(r.get(i).getJobPositionURL());
			rdto.get(i).setJobid(r.get(i).getId());
			
			rdto.get(i).setCompanyid(r.get(i).getCompany().getId());
			rdto.get(i).setCompanyname(r.get(i).getCompany().getName());
			rdto.get(i).setCompanystarRating(r.get(i).getCompany().getStarRating());
		}
		return rdto;
	}
	
	private List<WebUserJobDTO> generateBookmarkJobLists(List<BookmarkedJobs> r){
		List<WebUserJobDTO> rdto = new ArrayList<>(r.size());
		for(int i=0;i<r.size();i++) {
			rdto.add(new WebUserJobDTO());
			rdto.get(i).setJobTitle(r.get(i).getJob().getJobTitle());
			rdto.get(i).setJobIndustry(r.get(i).getJob().getJobIndustry());
			rdto.get(i).setJobqualification(r.get(i).getJob().getJobqualification());
			rdto.get(i).setJobDescription(r.get(i).getJob().getJobDescription());
			rdto.get(i).setAutismLevel(r.get(i).getJob().getAutismLevel());
			rdto.get(i).setJobStarRating(r.get(i).getJob().getJobStarRating());
			rdto.get(i).setJobPositionURL(r.get(i).getJob().getJobPositionURL());
			rdto.get(i).setJobid(r.get(i).getJob().getId());
			
			rdto.get(i).setCompanyid(r.get(i).getJob().getCompany().getId());
			rdto.get(i).setCompanyname(r.get(i).getJob().getCompany().getName());
			rdto.get(i).setCompanystarRating(r.get(i).getJob().getCompany().getStarRating());
			rdto.get(i).setBookmarkJobid(r.get(i).getId());
			rdto.get(i).setBookmarkDate(r.get(i).getBookmarkDate());
		}
		return rdto;
	}
	private List<WebUserJobDTO> generateViewJobLists(List<ViewedJobs> r){
		List<WebUserJobDTO> rdto = new ArrayList<>(r.size());
		for(int i=0;i<r.size();i++) {
			rdto.add(new WebUserJobDTO());
			rdto.get(i).setJobTitle(r.get(i).getJob().getJobTitle());
			rdto.get(i).setJobIndustry(r.get(i).getJob().getJobIndustry());
			rdto.get(i).setJobqualification(r.get(i).getJob().getJobqualification());
			rdto.get(i).setJobDescription(r.get(i).getJob().getJobDescription());
			rdto.get(i).setAutismLevel(r.get(i).getJob().getAutismLevel());
			rdto.get(i).setJobStarRating(r.get(i).getJob().getJobStarRating());
			rdto.get(i).setJobPositionURL(r.get(i).getJob().getJobPositionURL());
			rdto.get(i).setJobid(r.get(i).getJob().getId());
			
			rdto.get(i).setCompanyid(r.get(i).getJob().getCompany().getId());
			rdto.get(i).setCompanyname(r.get(i).getJob().getCompany().getName());
			rdto.get(i).setCompanystarRating(r.get(i).getJob().getCompany().getStarRating());
			rdto.get(i).setViewJobid(r.get(i).getId());
			rdto.get(i).setDateViewed(r.get(i).getDateViewed());
		}
		return rdto;
	}
}

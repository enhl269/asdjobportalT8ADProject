package sg.edu.iss.asdadt8.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;
import sg.edu.iss.asdadt8.repositories.ReviewRepository;
import sg.edu.iss.asdadt8.user.UserServiceImp;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

@Configuration 
@EnableScheduling
public class RequestTimer {
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	JobCheckService js;
	
	@Autowired
	ApplicantRepository arepo;
	
	@Autowired
	ReviewRepository rrepo;
	
	
	@Autowired
	Client client;
	
	String[] reviews = {
			"I love this company! the colleagues are friendly and nice!",
			"This is one of the best company I ever worked in",
			"I feel this company mistreat their staff",
			"The welfare of this company is great!",
			"Works are dynamic and offered lots of flexibility",
			"Work culture, diversity and inclusion, excellent support from Managers, Good company values, High job security",
			"Great work culture; Brilliant leadership and key people; Has emphasis in inclusion and diversity; Varied roles and extensive industry/domain scope",
			"Big focus on culture, health and wellbeing, diversity and inclusion and granular career performance path",
			"Good employee benefits, work culture, diverse teams, no bell curve and opportunities to learn",
			"Management is not good and a lot of politics based on language diversity"
	};
	


	public RequestTimer() {
	}
	
	

	public RequestTimer(JobRepository jrepo) {
		super();
		this.jrepo = jrepo;
	}
	
	@Scheduled(cron = "* * */12 * * ?") // every 12hour
    public void request() {
        System.out.println("it is time request job data" + LocalDateTime.now());
       save(client);   
	}
	
	public void save(Client client) {
		List<Job> jobs= js.checkAll(client.sendPostRequest());

		List<Review> jobsAssignReviews = assignReview(jobs);
		jrepo.saveAll(jobs);
		rrepo.saveAll(jobsAssignReviews);
	}
	
	public List<Review> assignReview(List<Job> jobs){
		List<Review> jobsAssignReviews = new ArrayList<>();
		
		for(Job job:jobs) {
			jobsAssignReviews.add(
					new Review(randomRating(),randomReview(),"Approved",job.getCompany(),randomDate(),job,randomApplicant()));
		}
		
		return jobsAssignReviews;
	}
	
	private long randomRating() {
		return (long) new Random().nextInt(5);
	}
	
	private String randomReview() {
		int random = (int)(new Random().nextInt(9-1)+1);
		return reviews[random];
	}
	
	private LocalDate randomDate() {
		LocalDate startDate = LocalDate.of(2021, 1, 1); //start date
	    long start = startDate.toEpochDay();
	    System.out.println(start);

	    LocalDate endDate = LocalDate.now(); //end date
	    long end = endDate.toEpochDay();
	    System.out.println(start);

	    long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
	    return LocalDate.ofEpochDay(randomEpochDay); // random date between the range
	}
	
	private Applicant randomApplicant() {
		int random = (int)(new Random().nextInt(9-1)+1);
		Applicant a = arepo.findOneApplicantByID((long)random);
		return a;
	}
	

}

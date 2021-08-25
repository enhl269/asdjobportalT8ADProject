package sg.edu.iss.asdadt8.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;

import java.time.*;
import java.util.List;

import javax.transaction.Transactional;

@Configuration 
@EnableScheduling
public class RequestTimer {
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	JobCheckService js;
	
	@Autowired
	Client client;

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
		jrepo.saveAll(jobs);
	}

}

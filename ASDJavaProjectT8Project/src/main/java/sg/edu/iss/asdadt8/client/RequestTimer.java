package sg.edu.iss.asdadt8.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.repositories.JobRepository;

import java.time.*;
import java.util.List;

import javax.transaction.Transactional;

@Configuration 
@EnableScheduling
public class RequestTimer {
	
	@Autowired
	JobRepository jrepo;
	

	public RequestTimer() {
	}
	
	

	public RequestTimer(JobRepository jrepo) {
		super();
		this.jrepo = jrepo;
	}

	//@Scheduled(cron = "0 0 23 * * ? ") //everyday
	//@Scheduled(cron = "* */60 * * * ?") // every 1 hour
	@Scheduled(cron = "* */30 * * * ?") // every 30 minutes
    public void request() {
        System.out.println("it is time request job data" + LocalDateTime.now());
        Client client = new Client();
        save(client);
        
	}
	
	public void save(Client client) {
		try {
		jrepo.saveAll(client.sendPostRequest());
		} catch(Exception e){}
	}

}

package sg.edu.iss.asdadt8.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.*;

@Configuration 
@EnableScheduling
public class RequestTimer {
	

	public RequestTimer() {
	}

	//@Scheduled(cron = "0 0 24 * * ? ") everyday
	@Scheduled(cron = "0/5 * * * * ?") // every 5 seconds
    public void request() {
        System.out.println("it is time request job data" + LocalDateTime.now());
        Client.sendGetRequest();
	}

}

package sg.edu.iss.asdadt8.client;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.repositories.JobRepository;

@Service
public class JobCheckService {
	
	@Autowired
	JobRepository jrepo;
	
	public List<Job> checkAll(List<Job> newJobList) {
		Iterator<Job> iterator=newJobList.iterator();
		for(Job job:jrepo.findAll()) {
			while(iterator.hasNext()) {
				Job j = iterator.next();
				if(j.equals(job)) 
					iterator.remove();
			}
		}
		return newJobList;
	}

}

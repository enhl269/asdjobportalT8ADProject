package sg.edu.iss.asdadt8.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;

@Component
public class Client {

	private static final String url = "http://47.241.209.188:5000/";

	@Autowired
	JobRepository jrepo;

	@Autowired
	CompanyRepository crepo;

	
	Company companyDefault; // job;

	public Client() {
		super();
	
	}

	public List<Job> sendPostRequest() {
		String keyword = RandomEnum.random(KeyWord.class).toString();
		System.out.println("request for " + keyword);
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod post = HttpMethod.POST;
		HashMap<String, Object> map = new HashMap<>();
		map.put("num", 10);
		map.put("keywords", keyword);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
		ResponseEntity<List<JobDTO>> response = client.exchange(url, post, requestEntity,
				new ParameterizedTypeReference<List<JobDTO>>() {
				});
		List<JobDTO> new_job = new ArrayList<>();
		new_job = response.getBody();
		List<Job> joblist = new ArrayList<>();
		for (JobDTO str : new_job) {
			companyDefault = crepo.findById(new Random().nextInt((int) (crepo.count()-2))+1);
			String jd;
			if(str.getJobDescription().length()>100)
				jd = str.getJobDescription().substring(0, 100) + "...";
			else 
				jd = str.getJobDescription();
			str.setJobDescription(jd);
			joblist.add(new Job(str.getJobTitle(), str.getJobIndustry(), str.getJobDescription(), str.getAutismLevel(),
					str.getJobPositionURL(), companyDefault));
		}
		System.out.println("create a request to " + url + ", " + response.getStatusCode());
		return joblist;
	}

}

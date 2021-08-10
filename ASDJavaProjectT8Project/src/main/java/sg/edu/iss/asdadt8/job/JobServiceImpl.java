package sg.edu.iss.asdadt8.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {


	@Autowired
	JobRepository jrepo;

	@Override
	public List<Job> showallJobs() {
		// TODO Auto-generated method stub
		return jrepo.findAll();
	}

	@Override
	public List<Job> findJobs(String searchKey) {
		// TODO Auto-generated method stub
		return jrepo.findJobByNameOrDesc(searchKey);
	}

	@Override
	public List<Job> filterJobs(String title,float jobStarRating, int autismLvl) {
		// TODO Auto-generated method stub
		return jrepo.filterJobs(title, jobStarRating, autismLvl);
	}

	@Override
	public Job findJobById(Long Id) {
		// TODO Auto-generated method stub
		return jrepo.findById(Id).get();
	}

	@Override
	public List<Job> categorizeJobs(String cateogirzeId) {
		// TODO Auto-generated method stub
		return jrepo.categorizeJobsList(cateogirzeId);
	}

	@Override
	public List<String> jobIndustryList() {
		// TODO Auto-generated method stub
		return jrepo.categorizeJobs();
	}
	
}

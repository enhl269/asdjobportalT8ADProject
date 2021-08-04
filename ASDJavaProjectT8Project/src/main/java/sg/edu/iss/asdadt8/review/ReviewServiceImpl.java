package sg.edu.iss.asdadt8.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Review;

@Service
public class ReviewServiceImpl {
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	public void delete(Long id) {
		rrepo.deleteById(id);
	}
	
	public void save(Review review) {
		rrepo.save(review);
	}

}

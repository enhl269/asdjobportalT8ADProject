package sg.edu.iss.asdadt8.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Override
	public void delete(Long id) {
		rrepo.deleteById(id);
	}
	@Override
	public Review save(Review review) {
		return rrepo.save(review);
	}
	@Override
	public List<Review> getAllReview() {
		return rrepo.findAll();
	}

	
	

}

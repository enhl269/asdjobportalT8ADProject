package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Review;

@Service
public class ReviewServiceImplWeb implements ReviewServiceWeb{
	
	@Autowired
	ReviewRepositoryWeb rrepo;
	
	@Override
	public List<Review> findAllReviews(){
		return rrepo.findAll();
	}
	
	@Override
	public List<Review> findApprovedReviews(){
		return rrepo.findApprovedReviews();
	}
	
	@Override
	public List<Review> findBlockedReviews(){
		return rrepo.findBlockedReviews();
	}
	
	@Override
	public void updateReviewStatus(Long id, String status){
		rrepo.saveStatus(id, status);
	}

}

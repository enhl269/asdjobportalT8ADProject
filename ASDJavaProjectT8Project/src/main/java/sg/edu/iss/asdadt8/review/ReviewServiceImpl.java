package sg.edu.iss.asdadt8.review;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
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

	public ReviewServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<CompaniesReviewDTO> showAllCompaniesReviews(){
		List<Company> company = crepo.findAll();
		List<CompaniesReviewDTO> companiesReview = new ArrayList<>();
		
		for(Company c: company){
			float averageRating = (float) company.stream().filter(x->x.getName()==c.getName()).mapToDouble(x->x.getStarRating()).average().getAsDouble();
			companiesReview.add(new CompaniesReviewDTO(c.getName().toString(),averageRating));
		}
		
		return companiesReview;
		
	}
	
	@Override
	public List<CompaniesReviewDTO> showByCompanyReview(String companyName){
		List<Company> company = crepo.findByReviewByCompany(companyName);
		List<CompaniesReviewDTO> companiesReview = new ArrayList<>();
		
		for(Company c: company) {
			float averageRating = (float) company.stream().filter(x->x.getName()==c.getName()).mapToDouble(x->x.getStarRating()).average().getAsDouble();
			List<Review> review = c.getReview();
			
			for(Review r: review)
			{
				companiesReview.add(new CompaniesReviewDTO(c.getName().toString(),averageRating,
						r.getReviewDescription(),r.getApplicant().toString(),r.getReviewstars(),r.getJob().getJobTitle()));
			}
			
			
		}
		
		
		return companiesReview;
		
	}
	


	

}

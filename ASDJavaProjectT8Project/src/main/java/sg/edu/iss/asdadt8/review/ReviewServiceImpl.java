package sg.edu.iss.asdadt8.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}

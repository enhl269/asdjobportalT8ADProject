package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Review;

public interface CompanyRepository extends JpaRepository<Company,Long>{

	@Query("SELECT c FROM Company c WHERE c.name =?1")
	List<Company> findByCompanyName(String CompanyName);
	
	@Query("SELECT c.reviews FROM Company c WHERE c.name =?1")
	List<Review> findByCompanyReview(String CompanyName);
	
	@Query("SELECT c FROM Company c")
	List<Company> findAll();
	
	@Query("SELECT c FROM Company c WHERE c.name =?1")
	List<Company> findByReviewByCompany(String CompanyName);
	

}

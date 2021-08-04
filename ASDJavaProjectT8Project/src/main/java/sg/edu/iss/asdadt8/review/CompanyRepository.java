package sg.edu.iss.asdadt8.review;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{

}

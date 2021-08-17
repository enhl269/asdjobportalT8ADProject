package sg.edu.iss.asdadt8.webuser;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.Review;

public interface ReviewRepositoryWebUser extends JpaRepository<Review,Long>{

}

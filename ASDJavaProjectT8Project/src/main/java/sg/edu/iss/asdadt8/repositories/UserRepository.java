package sg.edu.iss.asdadt8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}

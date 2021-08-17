package sg.edu.iss.asdadt8.user;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.User;

public interface UserService {
	
	User saveUser(User user);
	User getUser(String email);
	List<User> getUsers();
	void deleteApplicant(ApplicantDTO applicant);
	ApplicantDTO getApplicant(String username);
	void saveApplicant(ApplicantDTO applicant); //creat and update applicant
	void deleteUser(User userDelete);

}

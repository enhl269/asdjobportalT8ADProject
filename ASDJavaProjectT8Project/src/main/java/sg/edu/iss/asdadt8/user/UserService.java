package sg.edu.iss.asdadt8.user;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.AvatarFile;
import sg.edu.iss.asdadt8.domain.ResumeFile;
import sg.edu.iss.asdadt8.domain.User;

public interface UserService {
	
	User saveUser(User user);
	User getUser(String email);
	List<User> getUsers();
	void deleteApplicant(ApplicantDTO applicant);
	ApplicantDTO getApplicant(String username);
	void saveApplicant(ApplicantDTO applicant); //creat and update applicant

	void deleteUser(User userDelete);
	User getUserById(Long id);
	Optional<Applicant> findAllApplicantById(Long id);
	//void saveApplicant(Applicant applicant);
	List<ApplicantDTO> getApplicants();
	ResumeFile storeResume(String username, MultipartFile file) throws IOException;
	AvatarFile storeAvatar(String username, MultipartFile file) throws IOException;
	void saveResumeApplicant(ApplicantDTO dto) ;
	void saveAvatarApplicant(ApplicantDTO dto) ;
	AvatarFile getAvatar(String username);

}
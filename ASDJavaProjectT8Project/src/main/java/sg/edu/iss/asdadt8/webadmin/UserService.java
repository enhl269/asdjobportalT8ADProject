package sg.edu.iss.asdadt8.webadmin;

import java.util.List;

import sg.edu.iss.asdadt8.domain.Applicant;

public interface UserService {

	List<Applicant> findAllApplicant();

	List<Applicant> findAllApplicantByApproveStatus();

	List<Applicant> findAllApplicantByBlockStatus();

	void updateApplicantStatus(Long id, String status);

}

package sg.edu.iss.asdadt8.webadmin;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ApplicantRepository arepo;
	
	@Override
	public List<Applicant> findAllApplicant(){
		return arepo.findAllApplicant();
	}
	
	@Override
	public List<Applicant> findAllApplicantByApproveStatus(){
		return arepo.findApplicantByApproveStatus();
	}
	
	@Override
	public List<Applicant> findAllApplicantByBlockStatus(){
		return arepo.findApplicantByBlockStatus();
	}
	
	@Override
	public void updateApplicantStatus(Long id, String status){
		Applicant applicant = arepo.findOneApplicantByID(id);
		applicant.setUserStatus(status);
		arepo.save(applicant);	
	}
	
	
	
	
}

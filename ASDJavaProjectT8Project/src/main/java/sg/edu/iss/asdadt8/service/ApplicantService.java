package sg.edu.iss.asdadt8.service;

import sg.edu.iss.asdadt8.domain.Applicant;

public interface ApplicantService {
	
	public void saveApplicant(Applicant applicant);
	
	public void deleteApplicant(Applicant applicant);
	
	public void getApplicant(Long id);

}

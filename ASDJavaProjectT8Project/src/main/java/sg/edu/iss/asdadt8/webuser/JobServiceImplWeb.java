package sg.edu.iss.asdadt8.webuser;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.ViewedJobs;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class JobServiceImplWeb implements JobServiceWeb{
	
	@Autowired
	JobRepositoryWeb jrepo;
	
	@Autowired
	BookmarkJobRepositoryWeb brepo;
	
	@Autowired
	ViewedJobRepositoryWeb vrepo;
	
	@Autowired
	UserRepositoryWeb urepo;

	@Override
	public List<Job> showallJobs() {
		return jrepo.findAll();
	}

	@Override
	public List<Job> findJobs(String searchKey) {
		return jrepo.findJobByNameOrDesc(searchKey);
	}

	@Override
	public List<Job> filterJobs(String title,float jobStarRating, int autismLvl) {
		return jrepo.filterJobs(title, jobStarRating, autismLvl);
	}

	@Override
	public Job findJobById(Long Id) {
		return jrepo.findById(Id).get();
	}

	@Override
	public List<Job> categorizeJobs(String cateogirzeId) {
		return jrepo.categorizeJobsList(cateogirzeId);
	}

	@Override
	public List<String> jobIndustryList() {
		return jrepo.categorizeJobs();
	}
	
	public List<ViewedJobs> findViewedJobsByApplicantEmail(String username){
		return vrepo.findViewedJobsByUserEmail(username);
	}
	
	public List<BookmarkedJobs> findBookmarkedJobsByApplicantEmail(String username){
		return brepo.findBookmarkByUserEmail(username);
		}
	
	public String applyJobUrl(long jobid, String username) {
		Job j = applyJob(jobid, username);
		//return string company url address
		return j.getJobPositionURL();
	}
	
	public String applyJobEmail(long jobid, String username) {
		Job j = applyJob(jobid, username);
		Applicant user_session = urepo.findApplicantByEmail(username);

		if(j.getCompany().getHrEmail() != null) 
				{ 
					String gmail = "JL.T8.SA52@gmail.com"; //T8 ASD JOB PORTAL EMAIL
					String gpassword = "Toon*1234"; //T8 ASD JOB PORTAL PW

					String recipient1 = user_session.getEmail(); //USER EMAIL
					String recipient2 = j.getCompany().getHrEmail(); // JOB'S COMPANY HR EMAIL

						Properties prop = new Properties();
						prop.put("mail.smtp.host", "smtp.gmail.com");
						prop.put("mail.smtp.port", "587");
						prop.put("mail.smtp.auth", "true");
						prop.put("mail.smtp.starttls.enable", "true"); //TLS
						
						Session session = Session.getInstance(prop,
								new javax.mail.Authenticator() {
									protected PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication(gmail, gpassword);
									}
								});
							
						try {
							Message message = new MimeMessage(session);
							message.setFrom(new InternetAddress("testing@gmail.com"));
							message.setRecipients(
									Message.RecipientType.TO,
									InternetAddress.parse("johnsonleow@gmail.com")   //user_session :recipient1, recipient2 
							);
							message.setSubject("Application of " + j.getJobTitle());
							message.setText("Dear Hiring manager," 
									+ "\n\n I came across a very exciting position on your job portal that I believe fits me perfectly. I am interested in applying for the position of " 
									+ j.getJobTitle() + ". I would love to talk to you in more detail regarding this amazing opportunity at your company. It would give me great pleasure to hear back from you regarding my application. Thank you ! " 
									+ "\n\n =========Applicant Information========="
									+ "\n Name: " + user_session.getFirstName() + " " + user_session.getLastName()
									+ "\n Gender: " + user_session.getGender()
									+ "\n Email: " + user_session.getEmail()
									+ "\n Contact No: " + user_session.getContactNumber()
									+ "\n Resume URL: " + user_session.getResumeURl()
									+ "\n Introduction: " + user_session.getSelfIntroduction()
									+ "\n\n Regards,"
									+ "\n ASD Job Portal On Behalf of " + user_session.getFirstName() + " " + user_session.getLastName());
		
							Transport.send(message);
		
							System.out.println("Done");
		
						} catch (MessagingException f) {
							f.printStackTrace();
						}	
					}

		//return string company HR email
		return j.getCompany().getHrEmail();
	}
	
	@Override
	public void saveBookMark(long id,String username) {
		//get by id 
		Job job_details = jrepo.findById(id).get();
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
		
		//get all 
		List<Job> allJobs = showallJobs();
		List<BookmarkedJobs> allBmj = brepo.findAll();
		
		//checker- true when no duplicate book mark
		boolean check = true;
		
		//loop all book marks to check for duplicate
		for (int i = 0; i < allBmj.size() ; i++){
			if( user_session.getId() == allBmj.get(i).getApplicant().getId() && job_details.getId() == allBmj.get(i).getJob().getId() ) {
				check = false;
			}
		}
		
		if (check == true){
			jrepo.saveBookMark(job_details.getId(), user_session.getId(),localdate);
		}
	}
	
	private Job applyJob(long id, String username) {
		Job job_details = jrepo.findById(id).get();
		Applicant user_session= urepo.findApplicantByEmail(username);  //need to update to get user for session.
		LocalDate localdate = LocalDate.now();
		//get all 
		List<Job> allJobs = showallJobs();
		//get all applied jobs by applicant ID
		List<ViewedJobs> allApplied = vrepo.findViewedJobsByUserID(user_session.getId()); //need to update to get user for session.
		boolean check = true;		
		//check for duplicate in viewedjobs 
		for (int i = 0; i < allApplied.size() ; i++){
			if( user_session.getId() == allApplied.get(i).getApplicant().getId() && job_details.getId() == allApplied.get(i).getJob().getId() ) {
				check = false;
			}
		}
		//save to ViewedJobs (apply)
		if (check == true){
			jrepo.saveApplyJob(job_details.getId(), user_session.getId(),localdate);
		}
		return job_details;	
	}
} 
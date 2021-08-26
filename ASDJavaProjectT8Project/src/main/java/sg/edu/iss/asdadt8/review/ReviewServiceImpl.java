package sg.edu.iss.asdadt8.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.asdadt8.DTOs.CompaniesReviewDTO;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;
import sg.edu.iss.asdadt8.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Override
	public void delete(Long id) {
		rrepo.deleteById(id);
	}
	@Override
	public Review save(Review review) {
		return rrepo.save(review);
	}
	
	@Override
	public List<Review> getAllReview() {
		return rrepo.findAll();
	}

	public ReviewServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<CompaniesReviewDTO> showAllCompaniesReviews(){
		List<Company> company = crepo.findAll();
		List<CompaniesReviewDTO> companiesReview = new ArrayList<>();
		
		for(Company c: company){
			float averageRating = (float) company.stream().filter(x->x.getName()==c.getName()).mapToDouble(x->x.getStarRating()).average().getAsDouble();
			companiesReview.add(new CompaniesReviewDTO(c.getName().toString(),averageRating));
		}
		
		return companiesReview;
		
	}
	
	@Override
	public List<CompaniesReviewDTO> showByCompanyReview(String companyName){
		List<Company> company = crepo.findByReviewByCompany(companyName);
		List<CompaniesReviewDTO> companiesReview = new ArrayList<>();
		
		for(Company c: company) {
			float averageRating = (float) company.stream().filter(x->x.getName()==c.getName()).mapToDouble(x->x.getStarRating()).average().getAsDouble();
			List<Review> review = c.getReview();
			
			for(Review r: review)
			{
				companiesReview.add(new CompaniesReviewDTO(c.getName().toString(),averageRating,
						r.getReviewDescription(),r.getApplicant().toString(),r.getReviewstars(),r.getJob().getJobTitle()));
			}
		}
		return companiesReview;
	}
	
	@Override
	public void updateReviewStatus(Long id, String status){
		if(status.equals("Blocked")) {
			checkApplicant(id,"Blocked");
		}
		rrepo.saveStatus(id, status);	
	}
	
	public void checkApplicant(Long id, String status) {
		
		Applicant applicant = rrepo.findApplicantByReviewId(id, status);
		
		List<Review> reviews = arepo.findReviewByApplicantEmail(status, applicant.getEmail());
		if(reviews.size()>=3) {
			arepo.updateUserStatus(applicant.getId(),"Blocked");
			notifyApplicantBlockEmail(applicant.getEmail());
		}
		
	}
	@Override
	public List<Review> findApprovedReviews(){
		return rrepo.findApprovedReviews();
	}
	

	public boolean notifyApplicantBlockEmail(String username) {
		Applicant user_session = arepo.findByEmail(username);

		if(user_session != null) 
				{ 
					String gmail = "JL.T8.SA52@gmail.com"; //T8 ASD JOB PORTAL EMAIL
					String gpassword = "Toon*1234"; //T8 ASD JOB PORTAL PW

					String recipient1 = user_session.getEmail(); //USER EMAIL

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
									InternetAddress.parse("sizheng89@gmail.com")   //user_session :recipient1, recipient2 
							);
							message.setSubject("Notification of Member Being Blocked");
							message.setText("Dear Admin," 
									+ "\n Name: " + user_session.getFirstName() + " " + user_session.getLastName()
									+ "\n Has being blocked due to more than 4 blocked reviews being reported."
									+ "\n For your action to review member status."
									+ "\n Member Contact No: " + user_session.getContactNumber()
									+ "\n Member Email Address: " + user_session.getEmail()
									+ "\n"
									+ "\n\n Thank you,"
									+ "\n This message is system generated. Please do not reply");
		
							Transport.send(message);
		
							System.out.println("Done");
							return true;
		
						} catch (MessagingException f) {
							f.printStackTrace();

						}	
					}
		return false;

		
	}
	

}

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
import sg.edu.iss.asdadt8.DTOs.ReviewDTO;
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
	public List<ReviewDTO> findByCompanyReview(String companyName){
		List<Review> r = crepo.findByCompanyReview(companyName);
		return generateLists(filterApprovedReviews(r));
	}
	
	@Override 
	public List<ReviewDTO> findByApplicantReview(Long userid){
		List<Review> r = arepo.findByApplicantReview(userid);
		return generateLists(filterApprovedReviews(r));
	}
	
	@Override
	public List<ReviewDTO> findByJobReview(String jobTitle){
		List<Review> r = jrepo.findByJobReview(jobTitle);
		return generateLists(filterApprovedReviews(r));
	}
	
	@Override
	public List<ReviewDTO> findApprovedReviews(){
		List<Review> r = rrepo.findApprovedReviews();
		return generateLists(filterApprovedReviews(r));
	}
	
	@Override
	public List<ReviewDTO> findReviewsByCompanynameandJobTitle(String companyName, String jobTitle){
		List<Review> r = rrepo.findReviewsByCompanynameandJobTitle(companyName, jobTitle);
		return generateLists(filterApprovedReviews(r));
	}
	
	private List<Review> filterApprovedReviews(List<Review> r){
		
		List<Review> checked = new ArrayList<>();
		for(Review rr: r) {
			if(rr.getReviewStatus().equals("Approved")) {
				checked.add(rr);
			}
		}
		return checked;
	}
	
	private List<ReviewDTO> generateLists(List<Review> r){
		List<ReviewDTO> rdto = new ArrayList<>(r.size());
		for(int i=0;i<r.size();i++) {
			rdto.add(new ReviewDTO());
			rdto.get(i).setCompanyName(r.get(i).getCompany().getName());
			rdto.get(i).setUserId(r.get(i).getApplicant().getId());
			  rdto.get(i).setReviewstars(r.get(i).getReviewstars());
			  rdto.get(i).setReviewDescription(r.get(i).getReviewDescription());
			  rdto.get(i).setReviewDate(r.get(i).getReviewDate().toString());
			  rdto.get(i).setJobTitle(r.get(i).getJob().getJobTitle());
			  rdto.get(i).setApplicantName(r.get(i).getApplicant().getFirstName().toString() + " " + r.get(i).getApplicant().getLastName().toString());
			  rdto.get(i).setReviewId(r.get(i).getId());
		}
		return rdto;
	}
	@Override
	public void updateReviewStatus(Long id, String status){
		rrepo.saveStatus(id, status);	
	}

	@Override
	public Review ValidateReview(Review r, String s) {
		
		if(ValidateReview(s)){
        	r.setReviewStatus("Blocked");	
        	notifyAdminReviewBlocked();
        }
		return r;
	}
	
	private Boolean ValidateReview(String desc){
		String[] parts = desc.split(" "); 
		
		for(String part: parts) {
			for(BadKeyWords a: BadKeyWords.values()) {
				if (part.equals(a.toString())) {
					return true;}}}
		return false;
	}
	
	public void notifyAdminReviewBlocked() {

		String gmail = "JL.T8.SA52@gmail.com"; //T8 ASD JOB PORTAL EMAIL
		String gpassword = "Toon*1234"; //T8 ASD JOB PORTAL PW

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
				message.setSubject("Notification of Review Being Blocked");
				message.setText("Dear Admin," 
						+ "\n A Review has been blocked. Please login to update the status if need be."
						+ "\n\n Thank you,"
						+ "\n This message is system generated. Please do not reply");

				Transport.send(message);
				System.out.println("Done");

			} catch (MessagingException f) {
				f.printStackTrace();
			}	
		}
}

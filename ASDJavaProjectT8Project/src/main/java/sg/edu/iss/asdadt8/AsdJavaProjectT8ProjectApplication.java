package sg.edu.iss.asdadt8;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.domain.Role;
import sg.edu.iss.asdadt8.review.ApplicantRepository;
import sg.edu.iss.asdadt8.review.CompanyRepository;
import sg.edu.iss.asdadt8.review.JobRepository;
import sg.edu.iss.asdadt8.review.ReviewRepository;

@SpringBootApplication(scanBasePackages={
"sg.edu.iss.asdast8", "sg.edu.iss.asdast8.review"})
public class AsdJavaProjectT8ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsdJavaProjectT8ProjectApplication.class, args);
	}
	
	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	@Bean
	CommandLineRunner runner()  {
			return args->{
			
				LocalDate date = LocalDate.of(2021, 02, 01);
				
				Applicant user = new Applicant();
				user.setEmail("google@gmail.com");
				user.setPassword("123456");
				user.setFirstName("Larry");
				user.setLastName("Page");
				user.setContactNumber("86325472");
				user.setRoles(Role.APPLICANT.toString());
				user.setAvatarImageUrl("/blahblah");
				
				arepo.save(user);
				
				Company c = new Company("ABC Company", 4.2f, null, null);
				crepo.save(c);
				
				Job j = new Job("best job forever", 2, 3.5f, "/jobsback1",null,c,null,null,null);
				jrepo.save(j);
				
				Review r = new Review(4f, "best place to work", null, c, date,user);
				rrepo.save(r);
			};
	}

}

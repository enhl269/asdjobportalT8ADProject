package sg.edu.iss.asdadt8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.domain.Role;
import sg.edu.iss.asdadt8.review.ApplicantRepository;
import sg.edu.iss.asdadt8.review.CompanyRepository;
import sg.edu.iss.asdadt8.review.JobRepository;
import sg.edu.iss.asdadt8.review.ReviewRepository;

@SpringBootApplication
public class AsdJavaProjectT8ProjectApplication {


	@Autowired
	CompanyRepository crepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	ApplicantRepository arepo;
	
	public static void main(String[] args) {
		SpringApplication.run(AsdJavaProjectT8ProjectApplication.class, args);
	}
	
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

				Company c = new Company("ABC Company", 4.2f); //job
				
				Review r = new Review(4f, "best place to work", "status", c, null,user);
				
				Job j = new Job("best job forever", 2, 3.5f, "/jobsback1",null, c,r);
				
				arepo.save(user);
				crepo.save(c);
				rrepo.save(r);
				jrepo.save(j);
				
			};
	}

}

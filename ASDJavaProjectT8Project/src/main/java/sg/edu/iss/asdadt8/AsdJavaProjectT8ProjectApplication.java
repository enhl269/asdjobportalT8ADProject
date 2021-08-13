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
				user.setPassword("654321");
				user.setFirstName("Larry");
				user.setLastName("Page");
				user.setContactNumber("86325472");
				user.setRoles(Role.APPLICANT.toString());
				user.setAvatarImageUrl("/blahblah");
				user.setUserStatus("Approved");
				
				Applicant u2 = new Applicant();
				u2.setEmail("wankey@gmail.com");
				u2.setPassword("123456");
				u2.setFirstName("Wankey");
				u2.setLastName("Teo");
				u2.setContactNumber("96396872");
				u2.setRoles(Role.APPLICANT.toString());
				u2.setAvatarImageUrl("/bye");
				u2.setUserStatus("Approved");
				
				Applicant u3 = new Applicant();
				u3.setEmail("zhangran@gmail.com");
				u3.setPassword("123456");
				u3.setFirstName("Zhang");
				u3.setLastName("Ran");
				u3.setContactNumber("86396872");
				u3.setRoles(Role.APPLICANT.toString());
				u3.setUserStatus("Blocked");
				
				Applicant u4 = new Applicant();
				u4.setEmail("johnson@gmail.com");
				u4.setPassword("123456");
				u4.setFirstName("Johnson");
				u4.setLastName("Leow");
				u4.setContactNumber("96081872");
				u4.setRoles(Role.APPLICANT.toString());
				u4.setUserStatus("Blocked");
				
				Applicant u5 = new Applicant();
				u5.setEmail("noel@gmail.com");
				u5.setPassword("123456");
				u5.setFirstName("Noel");
				u5.setLastName("Wai");
				u5.setContactNumber("86080072");
				u5.setRoles(Role.APPLICANT.toString());
				u5.setUserStatus("Approved");
				

				Company c = new Company("ABC Company", null,4.2f); //job
				Company c1 = new Company("Digital Company", null,2.2f);
				Company c2 = new Company("Solution Design", null,3.2f);
				Company c3 = new Company("Gondola Team", null,4.7f);

				
				Job j = new Job("analyst","food","needs diploma","best job forever", 2, 3.5f, "/jobsback1",null, c,null);
				Job j1 = new Job("developer","education","needs degree","best job forever", 3, 3.5f, "/jobsback1",null, c1,null);
				Job j2 = new Job("architect","consulting","needs to talk","best job forever", 4, 3.5f, "/jobsback1",null, c2,null);
				Job j3 = new Job("designer","finance","needs nothing","best job forever", 3, 3.5f, "/jobsback1",null, c3,null);
				
				Review r = new Review(3.2f, "best place to work", "Approved",c,date, j,user);
				Review r1 = new Review(2.5f, "poor place", "Approved",c1,date, j1,u2);
				Review r2 = new Review(4.4f, "dirty place", "Approved",c,date, j2,u3);
				Review r3 = new Review(2.4f, "unremarkable place", "Approved",c3,date, j3,u4);
				Review r4 = new Review(1.4f, "great place", "Approved",c1,date, j1,user);
				Review r5 = new Review(4.3f, "trendy place", "Approved",c3,date, j3,u2);
				Review r6 = new Review(3.8f, "best2 place", "Blocked",c2,date, j2,u3);
				Review r7 = new Review(2.5f, "best1 place", "Blocked",c,date, j1,u4);
				
				
				arepo.save(user);
				arepo.save(u2);
				arepo.save(u3);
				arepo.save(u4);
				arepo.save(u5);
				
				crepo.save(c);
				crepo.save(c1);
				crepo.save(c2);
				crepo.save(c3);
				
				
				jrepo.save(j);
				jrepo.save(j1);
				jrepo.save(j2);
				jrepo.save(j3);
				
				rrepo.save(r);
				rrepo.save(r1);
				rrepo.save(r2);
				rrepo.save(r3);
				rrepo.save(r4);
				rrepo.save(r5);
				rrepo.save(r6);
				rrepo.save(r7);
				
			};
	}

}

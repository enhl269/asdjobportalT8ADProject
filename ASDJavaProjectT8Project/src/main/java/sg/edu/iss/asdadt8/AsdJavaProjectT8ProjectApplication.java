package sg.edu.iss.asdadt8;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sg.edu.iss.asdadt8.domain.Admin;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Company;
import sg.edu.iss.asdadt8.domain.Job;
import sg.edu.iss.asdadt8.domain.Review;
import sg.edu.iss.asdadt8.domain.Role;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;
import sg.edu.iss.asdadt8.repositories.CompanyRepository;
import sg.edu.iss.asdadt8.repositories.JobRepository;
import sg.edu.iss.asdadt8.repositories.ReviewRepository;
import sg.edu.iss.asdadt8.user.UserServiceImp;

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
	
	@Autowired
	UserServiceImp userService;
	
	public static void main(String[] args) {
		SpringApplication.run(AsdJavaProjectT8ProjectApplication.class, args);
	}
	
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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
				user.setUserStatus("Approve");
				
				Applicant u2 = new Applicant();
				u2.setEmail("wankey@gmail.com");
				u2.setPassword("123456");
				u2.setFirstName("Wankey");
				u2.setLastName("Teo");
				u2.setContactNumber("96396872");
				u2.setRoles(Role.APPLICANT.toString());
				u2.setAvatarImageUrl("/bye");
				u2.setUserStatus("Approve");
				
				Applicant u3 = new Applicant();
				u3.setEmail("zhangran@gmail.com");
				u3.setPassword("123456");
				u3.setFirstName("Zhang");
				u3.setLastName("Ran");
				u3.setContactNumber("86396872");
				u3.setRoles(Role.APPLICANT.toString());
				u3.setUserStatus("Approve");
				
				Applicant u4 = new Applicant();
				u4.setEmail("johnson@gmail.com");
				u4.setPassword("123456");
				u4.setFirstName("Johnson");
				u4.setLastName("Leow");
				u4.setContactNumber("96081872");
				u4.setRoles(Role.APPLICANT.toString());
				u4.setUserStatus("Approve");
				
				Applicant u5 = new Applicant();
				u5.setEmail("noel@gmail.com");
				u5.setPassword("123456");
				u5.setFirstName("Noel");
				u5.setLastName("Wai");
				u5.setContactNumber("86080072");
				u5.setRoles(Role.APPLICANT.toString());
				u5.setUserStatus("Approve");
				
				Admin u6 = new Admin();
				u6.setEmail("admin@gmail.com");
				u6.setPassword("123456");
				u6.setRoles(Role.ADMIN.toString());
				
				Company c = new Company("ABC Company","hr@abc.com", 4.2f); //job
				Company c1 = new Company("Digital Company","hr@digital.com", 2.2f);
				Company c2 = new Company("Solution Design","hr@solutiondesign.com", 3.2f);
				Company c3 = new Company("Gondola Team","hr@gondola.com", 4.7f);

				Job j = new Job("analyst","food","best job forever", 2, 2.5f, "https://www.nus.edu.sg/careers/",null, c,null);
				Job j1 = new Job("developer","food","best job forever", 3, 1.5f, "https://www.nus.edu.sg/careers/",null, c1,null);
				Job j2 = new Job("architect","food","best job forever", 4, 4.5f, "https://www.nus.edu.sg/careers/",null, c2,null);
				Job j3 = new Job("designer","education","best job forever", 3, 2.5f, "https://www.nus.edu.sg/careers/",null, c3,null);
				Job j4 = new Job("designer","consultation","best job forever", 3, 1.5f, "https://www.nus.edu.sg/careers/",null, c3,null);
				Job j5 = new Job("designer","digital solution","best job forever", 3, 4.5f, "https://www.nus.edu.sg/careers/",null, c3,null);

				Review r = new Review(3.2f, "best place to work", c,date, j,user);
				Review r1 = new Review(2.5f, "poor place", c1,date, j1,u2);
				Review r2 = new Review(4.4f, "dirty place", c,date, j2,u3);
				Review r3 = new Review(2.4f, "unremarkable place", c3,date, j3,u4);
				Review r4 = new Review(1.4f, "great place", c1,date, j1,user);
				Review r5 = new Review(4.3f, "trendy place", c3,date, j3,u2);
				Review r6 = new Review(3.8f, "best2 place", c2,date, j2,u3);
				Review r7 = new Review(2.5f, "best1 place", c,date, j1,u4);
				
				
				userService.saveUser(user);
				userService.saveUser(u2);
				userService.saveUser(u3);
				userService.saveUser(u4);
				userService.saveUser(u5);
				userService.saveUser(u6);
				
				crepo.save(c);
				crepo.save(c1);
				crepo.save(c2);
				crepo.save(c3);
				
				
				jrepo.save(j);
				jrepo.save(j1);
				jrepo.save(j2);
				jrepo.save(j3);
				jrepo.save(j4);
				jrepo.save(j5);
				
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

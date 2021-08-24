package sg.edu.iss.asdadt8.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import sg.edu.iss.asdadt8.domain.Role;
import sg.edu.iss.asdadt8.user.filter.CustomAuthenticationFilter;
import sg.edu.iss.asdadt8.user.filter.CustomAuthorizationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	  

	 
	@Autowired
	private UserDetailsService userDetailsService;

    @Bean @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
  //this was added to allow incoming request from localhost 3000
    //added by sz
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("HEAD", "GET", "PUT", "POST",
		"DELETE", "PATCH").allowedHeaders("*");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	
    	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
         
    	//disable csrf() and make it stateless for json
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
      //added by sz
       http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        
//       http.authorizeRequests().antMatchers("api/webuser/job/list/").anonymous();
       	http.authorizeRequests().antMatchers("/api/user/applicant",
       										"/api/user/applicant/*","/api/webuser/**",
       										"/api/review/job/company/**"
       										).permitAll();
        http.authorizeRequests().antMatchers("/api/user/refreshtoken",
        									 "/api/user/applicant/**",
//        									 "/api/webuser/**", 
        									 "/api/job/bookmark/**",
        									 "/api/job/applyjoburl/**",
        									 "/api/job/applyjobemail/**",
        									 "/api/job/shareurl/**",
        									 "/api/job/details/bookmark/list",
        									 "/api/job/details/viewed/list").authenticated(); 

        
        //http.authorizeRequests().antMatchers("/api/webadmin/**").authenticated(); 
        //http.authorizeRequests().antMatchers("/api/webuser/**").authenticated(); 
        //http.authorizeRequests().antMatchers("/api/webadmin/**").hasAuthority(Role.ADMIN.toString());
        //http.authorizeRequests().antMatchers("/api/jobadmin/**").hasAuthority(Role.APPLICANT.toString());    
        
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}

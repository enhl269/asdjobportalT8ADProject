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

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	
    	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
         
    	//disable csrf() and make it stateless for json
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        
        //http.authorizeRequests().antMatchers("/api/user/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/refreshtoken").authenticated(); 
        http.authorizeRequests().antMatchers("/api/user/applicant/**").authenticated();   
        http.authorizeRequests().antMatchers("/api/webadmin/**").hasAuthority(Role.ADMIN.toString());
        //http.authorizeRequests().antMatchers("/api/jobadmin/**").hasAuthority(Role.APPLICANT.toString());    
        
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}

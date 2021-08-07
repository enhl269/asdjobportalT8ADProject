package sg.edu.iss.asdadt8.account;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class WebsecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		return null;}
	
	
//	the AuthenticationManagerBuilder has a method called autheticationProvider which is going to add authenticationprovider
//	the Authenticationprovider is an interface 
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(null);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
	}

}

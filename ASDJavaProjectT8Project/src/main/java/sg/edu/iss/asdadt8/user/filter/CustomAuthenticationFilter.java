package sg.edu.iss.asdadt8.user.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.algorithms.Algorithm;

import sg.edu.iss.asdadt8.user.UserDTO;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Autowired
	private AuthenticationManager authenticationManager;

	//constructor
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
       String username = request.getParameter("username");
       String password = request.getParameter("password");

       UsernamePasswordAuthenticationToken authenticationToken 
       = new UsernamePasswordAuthenticationToken(username,password);
       
       return authenticationManager.authenticate(authenticationToken);
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
    		FilterChain chain, Authentication authentication) 
            throws IOException, ServletException{
    
        UserDTO userDto = (UserDTO)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    
    
    
    
    
    
    
    
    
    
    
    
    }

	
}

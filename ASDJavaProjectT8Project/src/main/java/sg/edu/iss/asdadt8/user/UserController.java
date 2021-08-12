
package sg.edu.iss.asdadt8.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.iss.asdadt8.domain.Admin;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.User;
import sg.edu.iss.asdadt8.domain.Role;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//this method intends to create and update an applicant, completed
	@PostMapping("/applicant")
    public ResponseEntity<ApplicantDTO> saveApplicant(@RequestBody ApplicantDTO applicant){
		//validate if there is a email
		if(applicant.getUsername()==null) {
			return ResponseEntity.badRequest().body(applicant);
		} 
		else {
        //check if it is a new user
			if(applicant.getId()==null) {
				//then it should be a new user or they dont pass the userid
				//if it is a new user
				try {applicant.setId(userService.getApplicant(applicant.getUsername()).getId());}
				catch (NullPointerException e){}
			    	if(applicant.getRoles()==null)
			    		applicant.setRoles(Role.APPLICANT.toString());
			} 
				// update or create applicant
			userService.saveApplicant(applicant);
			return ResponseEntity.ok().body(applicant);
		}		
	}
	

	//this method is to get applicant detail
	@GetMapping("/applicant/{username}")
	public ResponseEntity<ApplicantDTO> getApplicant(@PathVariable("username") String username){
		ApplicantDTO applicant = userService.getApplicant(username);
		if(applicant !=null) {
			return ResponseEntity.ok().body(applicant);
		} else {
			return ResponseEntity.badRequest().body(applicant);
		}
	}
	
	
	
	//this method intends to get all users(include admin and applicant) as a list
	@Secured("hasAuthority('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<User>> getUsers(){
	    return ResponseEntity.ok().body(userService.getUsers());
	}


	
	
	//this method intends to create and update admin,
	//to creat a new admin should be implemented by another admin user.
	@Secured("hasAuthority('ADMIN')")
	@PostMapping("/admin")
    public ResponseEntity<User> saveUser(@RequestBody Admin admin){
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/user").toString());
    	if(admin.getId()==null) {
    		if(admin.getEmail()==null)
    			return ResponseEntity.badRequest().body(admin);
    		else {
    			User userExist = userService.getUser(admin.getEmail());
    			if(userExist!=null)
            		admin.setId(userExist.getId());
    		}
    	}
    	if(admin.getRoles()==null) {
    		admin.setRoles(Role.ADMIN.toString());
    	}
		return ResponseEntity.ok().body(userService.saveUser(admin));
    }
	

    
    //this method intends to delete the user
    @DeleteMapping("/user")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
    	User userDelete = userService.getUser(user.getEmail());
    	if(userDelete!=null) {
	    	userService.deleteUser(userDelete);    
	    	return ResponseEntity.ok().body(userDelete);
    	} else {
    		return ResponseEntity.internalServerError().body(user);
    	}
    }
    
    //this method for refresh token, should be implement by front end
    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) 
    		throws IOException{
		/*
		 * get token from request,
		 * verify token,
		 * decode token,
		 * create new token throw
		 */
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
        
    	//the header(Authorization) should have prefix with "Bearer ", there is a space after bearer
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try{
                String token = authorizationHeader.substring("Bearer ".length());

                //the same algorithm with authentication filter,"secret" is a key word
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                //verify the jwt algorithm
                JWTVerifier verifier = JWT.require(algorithm).build();

                //decode jwt and get username and roles
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

                String access_token = JWT.create()
                        .withSubject(username)
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",roles)
                        .sign(algorithm);
                
                String refresh_token = JWT.create()
                        .withSubject(username)
                        .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",roles)
                        .sign(algorithm);
            
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token",refresh_token);
                tokens.put("username", username);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            } 
        }else {
        	throw new RuntimeException("refresh token is missing");
        }
    	
    }
    
    
    
}


package sg.edu.iss.asdadt8.user;


import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import sg.edu.iss.asdadt8.domain.AvatarFile;
import sg.edu.iss.asdadt8.domain.Admin;
import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.AvatarFile;
import sg.edu.iss.asdadt8.domain.User;
import sg.edu.iss.asdadt8.filetest.ResponseFile;
import sg.edu.iss.asdadt8.filetest.ResponseFileMessage;
import sg.edu.iss.asdadt8.domain.Role;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    //this method for refresh token, should be implement by front end, completed
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
	
	//this method intends to create an applicant, completed, no authentication
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

//	//save avatar image
//	@PostMapping("/applicant/uploadavatar/{username}")
//	  public ResponseEntity<HttpStatus> uploadAvatar(@PathVariable("username") String username, @RequestParam("avatar") MultipartFile avatar) {
//	    String message = "";
//	    try {
//	    	userService.storeAvatar(username,avatar);
//
//		      message = "Uploaded the file successfully: " + avatar.getOriginalFilename();
//		      return new ResponseEntity<>(HttpStatus.OK);
//	    } catch (Exception e) {
//	      message = "Could not upload the file: " + avatar.getOriginalFilename() + "!";
//	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }
//	
//	//save resume 
//	@PostMapping("/applicant/uploadresume/{username}")
//	  public ResponseEntity<HttpStatus> uploadResume(@PathVariable("username") String username, @RequestParam("resume") MultipartFile resume) {
//	    String message = "";
//	    try {
//	    	userService.storeResume(username,resume);
//
//	      message = "Uploaded the file successfully: " + resume.getOriginalFilename();
//	      return new ResponseEntity<>(HttpStatus.OK);
//	    } catch (Exception e) {
//	      message = "Could not upload the file: " + resume.getOriginalFilename() + "!";
//	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//	    }
//	  }

	
	//this method intends to update an applicant
	@PostMapping("/applicant/update")
    public ResponseEntity<ApplicantDTO> updateApplicant(@RequestBody ApplicantDTO applicant){
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
		    	if(applicant.getContactNumber()=="")
		    		applicant.setContactNumber(null);
			} 
				// update or create applicant
			userService.saveApplicant(applicant);
			return ResponseEntity.ok().body(applicant);
		}		
	}
	
	//this method is to get applicant detail, completed
	@GetMapping("/applicant/{username}")
	public ResponseEntity<ApplicantDTO> getApplicant(@PathVariable("username") String username){
		ApplicantDTO applicant = userService.getApplicant(username);
		if(applicant !=null) {
			return ResponseEntity.ok().body(applicant);
		} else {
			return ResponseEntity.badRequest().body(applicant);
		}
	}
		
    //this method intends to delete the user, completed
    @DeleteMapping("/applicant/{username}")
    public ResponseEntity<ApplicantDTO> deleteUser(@PathVariable("username") String username) {
		ApplicantDTO applicant = userService.getApplicant(username);
    	if(applicant!=null) {
	    	userService.deleteApplicant(applicant);    
	    	return ResponseEntity.ok().body(applicant);
    	} else {
    		return ResponseEntity.badRequest().body(applicant);
    	}
    }
	
	@PostMapping("/applicant/updateavatar/{username}")
    public ResponseEntity<ApplicantDTO> updateAvatar(@RequestParam("file") MultipartFile file, @PathVariable("username") String username) throws IOException{
		//added by sz
		userService.storeAvatar(username,file);
		
		String filename = file.getOriginalFilename();
		String suffixName = filename.substring(filename.lastIndexOf("."));
		filename = "avatar"+suffixName;
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		String avatarURL = staticPath + File.separator + username + File.separator + filename;
		File avatarFile = new File(avatarURL);
		if (!avatarFile.getParentFile().exists()) 
			avatarFile.getParentFile().mkdirs(); 

		try{
			file.transferTo(avatarFile);
		} 
		catch (IOException e) { e.printStackTrace();}
		ApplicantDTO a = userService.getApplicant(username);
		a.setAvatarImageURl(avatarURL);
		userService.saveApplicant(a);
		
		
		
		return ResponseEntity.ok().body(a);	
	}
	
	@PostMapping("/applicant/updateresume/{username}")
    public ResponseEntity<ApplicantDTO> updateResume(@RequestParam("file") MultipartFile file, @PathVariable("username") String username) {
		String filename = file.getOriginalFilename();
		String suffixName = filename.substring(filename.lastIndexOf("."));
		filename = "resume"+suffixName;
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		String resumeURL = staticPath + File.separator + username + File.separator + filename;
		File resumeFile = new File(resumeURL);
		if (!resumeFile.getParentFile().exists()) 
			resumeFile.getParentFile().mkdirs(); 

		try{
			file.transferTo(resumeFile);
		} 
		catch (IOException e) { e.printStackTrace();}
		ApplicantDTO a = userService.getApplicant(username);
		a.setResumeURl(resumeURL);
		userService.saveApplicant(a);
		return ResponseEntity.ok().body(a);	
	}
	

	@GetMapping("/applicant/avatar/{username}")
    public ResponseEntity<StreamingResponseBody> downloadavatar(@PathVariable("username") String username) 
    		throws FileNotFoundException{
    	ApplicantDTO applicant = userService.getApplicant(username);
		if(applicant.getAvatarImageURl() !=null) {			
	        InputStream inputStream = new FileInputStream(applicant.getAvatarImageURl());
	        StreamingResponseBody body = outputStream -> FileCopyUtils.copy(inputStream, outputStream);
	        return ResponseEntity.ok().body(body);
		} else {
			return ResponseEntity.badRequest().build();
		}
    }
	
	@GetMapping("/applicant/avatarweb/{username}")
    public ResponseEntity<String> downloadavatarweb(@PathVariable("username") String username) 
    		throws IOException{
    	ApplicantDTO applicant = userService.getApplicant(username);
		if(applicant.getAvatarImageURl() !=null) {			
	        InputStream inputStream = new FileInputStream(applicant.getAvatarImageURl());
	        String encoded = Base64Utils.encodeToString(inputStream.readAllBytes());
	        
	        return ResponseEntity.ok().body(encoded);
		} else {
			return ResponseEntity.badRequest().build();
		}
    }
	
	//@GetMapping("/applicant/avatar/{username}")
	  public String getListFiles(@PathVariable("username") String username) {
	    AvatarFile files = userService.getAvatar(username);
	    String encoded = Base64Utils.encodeToString(files.getData());
	    
	    
	    StreamingResponseBody body = outputStream -> files.getData();
//	    String cleanfiles = files.substring(1);
//	     String fileDownloadUri = ServletUriComponentsBuilder
//	          .fromCurrentContextPath()
//	          .path("/api/user/files/")
//	          .path(files.getId())
//	          .toUriString();
	    
	    

//	      return fileDownloadUri;
	    
	    return encoded;
	}
    
    //this method intends to get all users(include admin and applicant) as a list
	@Secured("hasAuthority('ADMIN')")
	@GetMapping("admin/list")
	public ResponseEntity<List<User>> getUsers(){
	    return ResponseEntity.ok().body(userService.getUsers());
	}
	//add by sz
	@GetMapping("/userlist")
	public ResponseEntity<List<ApplicantDTO>> getApplicants(){
	    return ResponseEntity.ok().body(userService.getApplicants());
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
    
    //this method intends to delete the admin
	@Secured("hasAuthority('ADMIN')")
    @DeleteMapping("/user/admin")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
    	User userDelete = userService.getUser(user.getEmail());
    	if(userDelete!=null) {
	    	userService.deleteUser(userDelete);    
	    	return ResponseEntity.ok().body(userDelete);
    	} else {
    		return ResponseEntity.internalServerError().body(user);
    	}
    }


    
    //used getmapping as i cannot get pass the CORS policy of chrome. Therefore edited the delete method thru get method instead 
    //this method intends to delete the user
      @GetMapping("/user/{id}")
      public ResponseEntity<HttpStatus>  deleteUserById(@PathVariable("id") Long id) {
      	User userDelete = userService.getUserById(id);
      	if(userDelete!=null) {
  	    	userService.deleteUser(userDelete);    
  	    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
      	} else {
      		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      	}
      }
      
      //added by sz from down here 
      @GetMapping("/list/applicant/{id}")
  	public ResponseEntity<Applicant> retrieveApplicant(@PathVariable("id") Long id) {
  		Optional<Applicant> applicant = userService.findAllApplicantById(id);
  		
  		if(applicant.isPresent()) return new ResponseEntity<>(applicant.get(),HttpStatus.OK);
  		
  			else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		
  	}
      
     
      
}
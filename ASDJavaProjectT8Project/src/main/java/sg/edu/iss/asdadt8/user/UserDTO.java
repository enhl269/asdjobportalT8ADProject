package sg.edu.iss.asdadt8.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class UserDTO {
	
	private Long id;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String username;
	
	private String lastName;
		
	private String roles;

}

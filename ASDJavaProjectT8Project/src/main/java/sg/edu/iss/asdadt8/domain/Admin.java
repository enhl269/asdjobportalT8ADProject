package sg.edu.iss.asdadt8.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Admin")
public class Admin extends User  {
	
	public Admin() {
		super();
	}
	
	

}
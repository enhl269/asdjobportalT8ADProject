package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="Role_TYPE")
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@SuppressWarnings("deprecation")
	@Column(nullable = false, unique = true, length = 45)
	@NotEmpty(message = "Email can not be empty.")
	@Email(message = "Enter valid e-mail")
	private String email;
	
	@SuppressWarnings("deprecation")
	@Column(nullable = false, length = 64)
	@NotNull(message="Please enter a password")
	@NotEmpty(message = "Password can not be empty.")
	private String password;
	
	@SuppressWarnings("deprecation")
	@Column(name = "first_name", nullable = false, length = 20)
	@NotNull(message = "First Name can not be empty.")
    @NotEmpty(message = "First Name can not be empty.")
	private String firstName;
	
	@SuppressWarnings("deprecation")
	@Column(name = "last_name", nullable = false, length = 20)
	@NotNull(message = "Last Name can not be empty.")
    @NotEmpty(message = "Last Name can not be empty.")
	private String lastName;
	
	@Size(min=8, message = "Mobile number should be of 8 digits.")
	private String contactNumber;
	
	private String avatarimageURl;
	
	private String roles;
	
	public String getAvatarImageUrl() {
		return avatarimageURl;
	}

	public void setAvatarImageUrl(String avatarimageURl) {
		this.avatarimageURl = avatarimageURl;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}

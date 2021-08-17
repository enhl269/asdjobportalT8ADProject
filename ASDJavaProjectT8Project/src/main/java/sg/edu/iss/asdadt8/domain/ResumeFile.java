package sg.edu.iss.asdadt8.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ResumeFile {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  private String id;

	  private String name;

	  private String type;

	  @Lob
	  private byte[] data;

	  private String username;
	  

	  public ResumeFile() {
	  }

	  public ResumeFile(String name, String type, byte[] data, String username) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	    this.username = username;
	  }
	  
	  

	  public ResumeFile(String id, String name, String type, byte[] data, String username) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.data = data;
		this.username = username;
	}

	public String getId() {
	    return id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getType() {
	    return type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	
	  
	  

}

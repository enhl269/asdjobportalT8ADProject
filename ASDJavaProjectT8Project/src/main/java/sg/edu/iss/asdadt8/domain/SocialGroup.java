package sg.edu.iss.asdadt8.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class SocialGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany
	@JsonBackReference
	private List<Applicant> applicants = new ArrayList<>();

	public SocialGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SocialGroup(long id, List<Applicant> applicants) {
		super();
		this.id = id;
		this.applicants = applicants;
	}

	public SocialGroup(List<Applicant> applicants) {
		super();
		this.applicants = applicants;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	
	

}

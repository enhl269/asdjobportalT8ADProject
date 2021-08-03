package sg.edu.iss.asdadt8.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String skillSet;
	
	private String education;
	
	private String experience;
	
	@OneToOne
	private Applicant applicant;

	public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resume(long id, String skillSet, String education, String experience, Applicant applicant) {
		super();
		this.id = id;
		this.skillSet = skillSet;
		this.education = education;
		this.experience = experience;
		this.applicant = applicant;
	}

	public Resume(String skillSet, String education, String experience, Applicant applicant) {
		super();
		this.skillSet = skillSet;
		this.education = education;
		this.experience = experience;
		this.applicant = applicant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	
	

}

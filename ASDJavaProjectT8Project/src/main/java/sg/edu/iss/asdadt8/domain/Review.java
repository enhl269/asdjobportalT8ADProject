package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private float reviewstars;
	
	private String reviewDescription;
	
	private String reviewStatus;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate reviewDate;
	
	@ManyToOne
	private Company company;
	
	@OneToOne
	private Job job;
	
	@OneToOne
	private Applicant applicant;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(long id, float reviewstars, String reviewDescription, String reviewStatus, Company company, LocalDate reviewDate,
			Applicant applicant) {
		super();
		this.id = id;
		this.reviewstars = reviewstars;
		this.reviewDescription = reviewDescription;
		this.reviewStatus = reviewStatus;
		this.company = company;
		this.applicant = applicant;
		this.reviewDate=reviewDate;
	}
	
	public Review(float reviewstars, String reviewDescription, String reviewStatus, LocalDate reviewDate) {
		super();

		this.reviewstars = reviewstars;
		this.reviewDescription = reviewDescription;
		this.reviewStatus = reviewStatus;
		this.reviewDate=reviewDate;
	}

	public Review(float reviewstars, String reviewDescription, String reviewStatus, Company company,LocalDate reviewDate,
			Applicant applicant) {
		super();
		this.reviewstars = reviewstars;
		this.reviewDescription = reviewDescription;
		this.reviewStatus = reviewStatus;
		this.company = company;
		this.applicant = applicant;
		this.reviewDate=reviewDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getReviewstars() {
		return reviewstars;
	}

	public void setReviewstars(float reviewstars) {
		this.reviewstars = reviewstars;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

}

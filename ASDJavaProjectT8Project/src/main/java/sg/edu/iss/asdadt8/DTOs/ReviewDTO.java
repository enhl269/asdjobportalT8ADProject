package sg.edu.iss.asdadt8.DTOs;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ReviewDTO {
	
	//private long id;
	private float reviewstars;
	//private float reviewstarscompany;
	//private float reviewstarsjob;
	private String reviewDescription;
	//private String reviewStatus;
	//private long reviewid;
	
	//@DateTimeFormat (pattern="yyyy-MM-dd")
	private String reviewDate;
	
	//private long companyId;
	private String companyName;
	//private long jobId;
	private String jobTitle;
	//private String jobIndustry;
	private long userId;
	private String applicantName;
	
	
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReviewDTO(float reviewstars,String companyName,long userId,
			String reviewDescription, 
			String jobTitle, String reviewDate, String applicantName) {
		super();
		//this.id = id;
		this.reviewstars = reviewstars;
		//this.reviewstarscompany = reviewstarscompany;
		//this.reviewstarsjob = reviewstarsjob;
		this.reviewDescription = reviewDescription;
		//this.reviewStatus = reviewStatus;
		//this.reviewid=reviewid;
		this.reviewDate = reviewDate;
		//this.companyId = companyId;
		this.companyName = companyName;
		//this.jobId = jobId;
		this.jobTitle = jobTitle;
		//this.jobIndustry = jobIndustry;
		this.userId = userId;
		this.applicantName = applicantName;
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


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getReviewDate() 
	{ 
		return reviewDate; 
	}
	 
	 public void setReviewDate(String reviewDate) { this.reviewDate =
	 reviewDate; }
	 
	 public String getApplicantName() { return applicantName; }
	  
	  
	  public void setApplicantName(String applicantName) { this.applicantName =
	  applicantName; }
	
	

	/*
	 * public long getId() { return id; }
	 * 
	 * 
	 * public void setId(long id) { this.id = id; }
	 * 
	 * 
	 * public float getReviewstars() { return reviewstars; }
	 * 
	 * 
	 * public void setReviewstars(float reviewstars) { this.reviewstars =
	 * reviewstars; }
	 * 
	 * 
	 * public float getReviewstarscompany() { return reviewstarscompany; }
	 * 
	 * 
	 * public void setReviewstarscompany(float reviewstarscompany) {
	 * this.reviewstarscompany = reviewstarscompany; }
	 * 
	 * 
	 * public float getReviewstarsjob() { return reviewstarsjob; }
	 * 
	 * 
	 * public void setReviewstarsjob(float reviewstarsjob) { this.reviewstarsjob =
	 * reviewstarsjob; }
	 * 
	 * 
	 * public String getReviewDescription() { return reviewDescription; }
	 * 
	 * 
	 * public void setReviewDescription(String reviewDescription) {
	 * this.reviewDescription = reviewDescription; }
	 * 
	 * 
	 * public String getReviewStatus() { return reviewStatus; }
	 * 
	 * 
	 * public void setReviewStatus(String reviewStatus) { this.reviewStatus =
	 * reviewStatus; }
	 * 
	 * public long getReviewId() { return reviewid; }
	 * 
	 * 
	 * public void setReviewId(long reviewid) { this.reviewid = reviewid; }
	 * 
	 * 
	 * public LocalDate getReviewDate() { return reviewDate; }
	 * 
	 * 
	 * public void setReviewDate(LocalDate reviewDate) { this.reviewDate =
	 * reviewDate; }
	 * 
	 * 
	 * public long getCompanyId() { return companyId; }
	 * 
	 * 
	 * public void setCompanyId(long companyId) { this.companyId = companyId; }
	 * 
	 * 
	 * public String getCompanyName() { return companyName; }
	 * 
	 * 
	 * public void setCompanyName(String companyName) { this.companyName =
	 * companyName; }
	 * 
	 * 
	 * public long getJobId() { return jobId; }
	 * 
	 * 
	 * public void setJobId(long jobId) { this.jobId = jobId; }
	 * 
	 * 
	 * public String getJobTitle() { return jobTitle; }
	 * 
	 * 
	 * public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
	 * 
	 * 
	 * public String getJobIndustry() { return jobIndustry; }
	 * 
	 * 
	 * public void setJobIndustry(String jobIndustry) { this.jobIndustry =
	 * jobIndustry; }
	 * 
	 * 
	 * public long getUserId() { return userId; }
	 * 
	 * 
	 * public void setUserId(long userId) { this.userId = userId; }
	 * 
	 * 
	 * public String getApplicantName() { return applicantName; }
	 * 
	 * 
	 * public void setApplicantName(String applicantName) { this.applicantName =
	 * applicantName; }
	 * 
	 */

}

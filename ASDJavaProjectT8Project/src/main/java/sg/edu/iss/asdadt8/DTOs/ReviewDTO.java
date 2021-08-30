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
	  private long reviewid;
	  
	  //@DateTimeFormat (pattern="yyyy-MM-dd")
	  private String reviewDate;
	  
	  //private long companyId;
	  private String companyName;
	  //private long jobId;
	  private String jobTitle;
	  //private String jobIndustry;
	  private long userId;
	  private String applicantName;
	  private long reviewId;
	  
	  
	  public long getReviewid() {
	    return reviewid;
	  }


	  public void setReviewid(long reviewid) {
	    this.reviewid = reviewid;
	  }


	  public ReviewDTO() {
	    super();
	    // TODO Auto-generated constructor stub
	  }


	  public ReviewDTO(float reviewstars,String companyName,long userId,
	      String reviewDescription, 
	      String jobTitle, String reviewDate, String applicantName, long reviewid) {
	    super();
	    //this.id = id;
	    this.reviewstars = reviewstars;
	    //this.reviewstarscompany = reviewstarscompany;
	    //this.reviewstarsjob = reviewstarsjob;
	    this.reviewDescription = reviewDescription;
	    //this.reviewStatus = reviewStatus;
	    this.reviewid=reviewid;
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
	    
	    public long getReviewId() { return reviewid; }
	     
	      
	  public void setReviewId(long reviewid) { this.reviewid = reviewid; }
	  
	  

	  

	}
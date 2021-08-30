package sg.edu.iss.asdadt8.DTOs;

import java.time.LocalDate;

public class BookmarkedJobsDTO {
	  
	  private long id;
	  private String jobtitle;
	  private String companyname;
	  private String bookmarkDate;
	  private long jobDetailId;
	  
	  public BookmarkedJobsDTO() {

	  }

	  public BookmarkedJobsDTO(long id, String jobtitle, String companyname, String bookmarkDate,long jobDetailId) {
	    super();
	    this.id = id;
	    this.jobtitle = jobtitle;
	    this.companyname = companyname;
	    this.bookmarkDate = bookmarkDate;
	    this.jobDetailId = jobDetailId;
	  }
	  
	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
	    this.id = id;
	  }
	  public String getJobtitle() {
	    return jobtitle;
	  }
	  public void setJobtitle(String jobtitle) {
	    this.jobtitle = jobtitle;
	  }
	  public long getJobDetailId() {
	    return jobDetailId;
	  }

	  public void setJobDetailId(long jobDetailId) {
	    this.jobDetailId = jobDetailId;
	  }

	  public String getCompanyname() {
	    return companyname;
	  }
	  public void setCompanyname(String companyname) {
	    this.companyname = companyname;
	  }
	  public String getBookmarkDate() {
	    return bookmarkDate;
	  }
	  public void setBookmarkDate(String bookmarkDate) {
	    this.bookmarkDate = bookmarkDate;
	  }
	  
	}

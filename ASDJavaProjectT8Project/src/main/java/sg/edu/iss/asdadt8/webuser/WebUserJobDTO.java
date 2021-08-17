package sg.edu.iss.asdadt8.webuser;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class WebUserJobDTO {
	
	private String jobTitle;
	private String jobIndustry;
	private String jobqualification;
	private String jobDescription;
	private int autismLevel;
	private float jobStarRating;
	private String jobPositionURL;
	
	private long applicantid;
	private long jobid;
	private long companyid;
	private long viewjobid;
	private long bookmarkjobid;
	private String companyname;
	private float companystarRating;
	
	private LocalDate bookmarkDate;
	private LocalDate dateViewed;
	
	public WebUserJobDTO() {
		super();
	}
	
	public WebUserJobDTO(String jobTitle, String jobIndustry, String jobqualification, String jobDescription,
			int autismLevel, float jobStarRating, String jobPositionURL, long applicantid, long jobid, long companyid,
			long viewjobid,long bookmarkjobid,String companyname, float companystarRating, LocalDate bookmarkDate, LocalDate dateViewed) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.applicantid = applicantid;
		this.jobid = jobid;
		this.companyid = companyid;
		this.viewjobid = viewjobid;
		this.bookmarkjobid = bookmarkjobid;
		this.companyname = companyname;
		this.companystarRating = companystarRating;
		this.bookmarkDate = bookmarkDate;
		this.dateViewed = dateViewed;
	}
	
	public WebUserJobDTO(String jobTitle, String jobIndustry, String jobqualification, String jobDescription,
			int autismLevel, float jobStarRating, String jobPositionURL, long jobid, long companyid,
			String companyname, float companystarRating) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.jobid = jobid;
		this.companyid = companyid;
		this.companyname = companyname;
		this.companystarRating = companystarRating;
	}
	
	public WebUserJobDTO(String jobTitle, String jobIndustry, String jobqualification, String jobDescription,
			int autismLevel, float jobStarRating, String jobPositionURL,String companyname,long viewjobid, float companystarRating, LocalDate dateViewed) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.viewjobid = viewjobid;
		this.companyname = companyname;
		this.companystarRating = companystarRating;
		this.dateViewed = dateViewed;
	}
	
	public WebUserJobDTO(String jobTitle, String jobIndustry, String jobqualification, String jobDescription,
			int autismLevel, float jobStarRating, String jobPositionURL, long bookmarkjobid,String companyname, float companystarRating, LocalDate bookmarkDate) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.bookmarkjobid = bookmarkjobid;
		this.companyname = companyname;
		this.companystarRating = companystarRating;
		this.bookmarkDate = bookmarkDate;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobIndustry() {
		return jobIndustry;
	}
	public void setJobIndustry(String jobIndustry) {
		this.jobIndustry = jobIndustry;
	}
	public String getJobqualification() {
		return jobqualification;
	}
	public void setJobqualification(String jobqualification) {
		this.jobqualification = jobqualification;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public int getAutismLevel() {
		return autismLevel;
	}
	public void setAutismLevel(int autismLevel) {
		this.autismLevel = autismLevel;
	}
	public float getJobStarRating() {
		return jobStarRating;
	}
	public void setJobStarRating(float jobStarRating) {
		this.jobStarRating = jobStarRating;
	}
	public String getJobPositionURL() {
		return jobPositionURL;
	}
	public void setJobPositionURL(String jobPositionURL) {
		this.jobPositionURL = jobPositionURL;
	}
	public long getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(long applicantid) {
		this.applicantid = applicantid;
	}
	public long getJobid() {
		return jobid;
	}
	public void setJobid(long jobid) {
		this.jobid = jobid;
	}
	public long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}
	public long getViewJobid() {
		return viewjobid;
	}
	public void setViewJobid(long viewjobid) {
		this.viewjobid = viewjobid;
	}
	public long getBookmarkJobid() {
		return bookmarkjobid;
	}
	public void setBookmarkJobid(long bookmarkjobid) {
		this.bookmarkjobid = bookmarkjobid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public float getCompanystarRating() {
		return companystarRating;
	}
	public void setCompanystarRating(float companystarRating) {
		this.companystarRating = companystarRating;
	}
	public LocalDate getBookmarkDate() {
		return bookmarkDate;
	}
	public void setBookmarkDate(LocalDate bookmarkDate) {
		this.bookmarkDate = bookmarkDate;
	}
	public LocalDate getDateViewed() {
		return dateViewed;
	}
	public void setDateViewed(LocalDate dateViewed) {
		this.dateViewed = dateViewed;
	}
}

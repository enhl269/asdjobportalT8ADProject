package sg.edu.iss.asdadt8.DTOs;

public class JobAdminDTO {
	
private long id;
	
	private String jobTitle;
	private String jobIndustry;
	private String jobqualification;
	private String jobDescription;
	private int autismLevel;
	private float jobStarRating;
	private String jobPositionURL;
	
	private String companyname;
	private String companyemail;

	public JobAdminDTO(long id, String jobTitle, String jobIndustry, String jobqualification, String jobDescription, int autismLevel,
			float jobStarRating, String jobPositionURL, String companyname, String companyemail) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.companyname = companyname;
		this.companyemail = companyemail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getJobqualification() {
		return jobqualification;
	}

	public void setJobqualification(String jobqualification) {
		this.jobqualification = jobqualification;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	
}

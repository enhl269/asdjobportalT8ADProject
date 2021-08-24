package sg.edu.iss.asdadt8.client;

public class JobDTO {

	private long id;
	
	private String jobTitle;
	
    private String jobIndustry;
	
	private String jobqualification;
	
	private String jobDescription;
	
	private int autismLevel;
	
	private float jobStarRating;
	
	private String jobPositionURL;
	

	public JobDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JobDTO(String jobTitle, String jobIndustry, String jobDescription, int autismLevel,
			String jobPositionURL) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobPositionURL = jobPositionURL;
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

	public String getJobPositionURL() {
		return jobPositionURL;
	}

	public void setJobPositionURL(String jobPositionURL) {
		this.jobPositionURL = jobPositionURL;
	}

	@Override
	public String toString() {
		return "JobDTO [id=" + id + ", jobTitle=" + jobTitle + ", jobIndustry=" + jobIndustry + ", jobqualification="
				+ jobqualification + ", jobDescription=" + jobDescription + ", autismLevel=" + autismLevel
				+ ", jobStarRating=" + jobStarRating + ", jobPositionURL=" + jobPositionURL + "]";
	}

	

	
	


}

package sg.edu.iss.asdadt8.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Job {

	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String jobTitle;
	
	private String jobIndustry;

	private String jobqualification;

	private String jobDescription;

	private int autismLevel;

	private float jobStarRating;

	@Column(columnDefinition = "LONGTEXT")
	private String jobPositionURL;

	@ElementCollection(targetClass = String.class)
	private List<String> tags;

	@ManyToOne
	@JsonBackReference
	private Company company;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ViewedJobs> viewedJobs = new ArrayList<>();


	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	@JsonManagedReference 
	private List<BookmarkedJobs> bookmarkedJobs = new ArrayList<>();

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Review> reviews = new ArrayList<>();

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(long id, String jobTitle, String jobIndustry, String jobqualification, String jobDescription,
			int autismLevel, float jobStarRating, String jobPositionURL, List<String> tags, Company company,
			List<ViewedJobs> viewedJobs, List<BookmarkedJobs> bookmarkedJobs, List<Review> reviews) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.tags = tags;
		this.company = company;
		this.viewedJobs = viewedJobs;
		this.bookmarkedJobs = bookmarkedJobs; 
		this.reviews = reviews;
	}

	public Job(long id, String jobTitle, String jobIndustry, String jobDescription, int autismLevel,
			float jobStarRating, String jobPositionURL, List<String> tags, Company company, List<ViewedJobs> viewedJobs,
			List<BookmarkedJobs> bookmarkedJobs, List<Review> reviews) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.tags = tags;
		this.company = company;
		this.viewedJobs = viewedJobs;
		this.bookmarkedJobs = bookmarkedJobs;
		this.reviews = reviews;
	}

	public Job(String jobTitle, String jobIndustry, String jobqualification, String jobDescription, int autismLevel,
			float jobStarRating, String jobPositionURL, List<String> tags, Company company, List<Review> reviews) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.tags = tags;
		this.company = company;
		this.reviews = reviews;
	}

	public Job(String jobTitle, String jobIndustry, String jobDescription, int autismLevel, float jobStarRating,
			String jobPositionURL, List<String> tags, Company company, List<ViewedJobs> viewedJobs,
			List<BookmarkedJobs> bookmarkedJobs, List<Review> reviews) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.tags = tags;
		this.company = company;
		this.viewedJobs = viewedJobs;
		this.bookmarkedJobs = bookmarkedJobs;
		this.reviews = reviews;
	}

	public Job(String jobTitle, String jobIndustry, String jobqualification, String jobDescription, int autismLevel,
			float jobStarRating, String jobPositionURL, List<String> tags, Company company, List<ViewedJobs> viewedJobs,
			List<BookmarkedJobs> bookmarkedJobs, List<Review> reviews) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.jobqualification = jobqualification;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobStarRating = jobStarRating;
		this.jobPositionURL = jobPositionURL;
		this.tags = tags;
		this.company = company;
		this.viewedJobs = viewedJobs;
		this.bookmarkedJobs = bookmarkedJobs;
		this.reviews = reviews;
	}

	public Job(String jobTitle, String jobIndustry, String jobDescription, int autismLevel, String jobPositionURL,
			Company company) {
		super();
		this.jobTitle = jobTitle;
		this.jobIndustry = jobIndustry;
		this.company = company;
		this.jobDescription = jobDescription;
		this.autismLevel = autismLevel;
		this.jobPositionURL = jobPositionURL;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<ViewedJobs> getViewedJobs() {
		return viewedJobs;
	}

	public void setViewedJobs(List<ViewedJobs> viewedJobs) {
		this.viewedJobs = viewedJobs;
	}


	 public List<BookmarkedJobs> getBookmarkedJobs() { return bookmarkedJobs; }
	 
	 public void setBookmarkedJobs(List<BookmarkedJobs> bookmarkedJobs) {
	 this.bookmarkedJobs = bookmarkedJobs; }


	public List<Review> getReview() {
		return reviews;
	}

	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getJobqualification() {
		return jobqualification;
	}

	public void setJobqualification(String jobqualification) {
		this.jobqualification = jobqualification;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autismLevel, jobDescription, jobIndustry, jobPositionURL, jobTitle, jobqualification);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		return autismLevel == other.autismLevel && Objects.equals(jobDescription, other.jobDescription)
				&& Objects.equals(jobIndustry, other.jobIndustry)
				&& Objects.equals(jobPositionURL, other.jobPositionURL) && Objects.equals(jobTitle, other.jobTitle)
				&& Objects.equals(jobqualification, other.jobqualification);
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", jobTitle=" + jobTitle + ", jobIndustry=" + jobIndustry + ", jobqualification="
				+ jobqualification + ", jobDescription=" + jobDescription + ", autismLevel=" + autismLevel
				+ ", jobPositionURL=" + jobPositionURL + "]";
	}

}

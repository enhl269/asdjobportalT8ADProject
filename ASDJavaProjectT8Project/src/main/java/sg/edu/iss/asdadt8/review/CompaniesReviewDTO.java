package sg.edu.iss.asdadt8.review;

public class CompaniesReviewDTO {

	private String companyName;
	private float ratings;
	private String review;
	private String user;
	private float userRating;
	private String jobTitle;
	
	
	public float getUserRating() {
		return userRating;
	}
	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public float getRatings() {
		return ratings;
	}
	public void setRatings(float ratings) {
		this.ratings = ratings;
	}
	public CompaniesReviewDTO(String companyName, float ratings) {
		super();
		this.companyName = companyName;
		this.ratings = ratings;
	}
	
	
	public CompaniesReviewDTO(String companyName, float ratings, String review, String user, float userRating,
			String jobTitle) {
		super();
		this.companyName = companyName;
		this.ratings = ratings;
		this.review = review;
		this.user = user;
		this.userRating = userRating;
		this.jobTitle = jobTitle;
	}
	public CompaniesReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompaniesReviewDTO other = (CompaniesReviewDTO) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CompaniesReviewDTO [companyName=" + companyName + ", ratings=" + ratings + "]";
	}
	
	
}

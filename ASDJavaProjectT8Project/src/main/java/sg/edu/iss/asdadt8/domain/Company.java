package sg.edu.iss.asdadt8.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String hrEmail;
	
	private float starRating;
	
	@OneToMany (mappedBy="company", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Review> reviews = new ArrayList<>();
	
	@OneToMany (mappedBy="company", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Job> job = new ArrayList<>();

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(String name, String hrEmail, float starRating) {
		super();
		this.name = name;
		this.hrEmail = hrEmail;
		this.starRating = starRating;
	}

	public Company(long id, String name, String hrEmail, float starRating, List<Review> reviews, List<Job> job) {
		super();
		this.id = id;
		this.name = name;
		this.hrEmail = hrEmail;
		this.starRating = starRating;
		this.reviews = reviews;
		this.job = job;
	}

	public Company(String name, float starRating, List<Review> reviews, List<Job> job) {
		super();
		this.name = name;
		this.starRating = starRating;
		this.reviews = reviews;
		this.job = job;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getStarRating() {
		return starRating;
	}

	public void setStarRating(float starRating) {
		this.starRating = starRating;
	}


	public List<Review> getReview() {
		return reviews;
	}

	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	public String getHrEmail() {
		return hrEmail;
	}
	
	public void setHrEmail(String hrEmail) {
		this.hrEmail = hrEmail;
	}
	
	

}

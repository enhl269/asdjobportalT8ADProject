package sg.edu.iss.asdadt8.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private float starRating;
	
	@OneToOne (mappedBy="company", cascade = CascadeType.ALL)
	private Review review;
	
	@OneToMany (mappedBy="company", cascade = CascadeType.ALL)
	private List<Job> job = new ArrayList<>();

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(long id, String name, float starRating, Review review, List<Job> job) {
		super();
		this.id = id;
		this.name = name;
		this.starRating = starRating;
		this.review = review;
		this.job = job;
	}

	public Company(String name, float starRating, Review review, List<Job> job) {
		super();
		this.name = name;
		this.starRating = starRating;
		this.review = review;
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


	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	
	

}

package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class ViewedJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Job job;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Applicant applicant;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate dateViewed;

	public ViewedJobs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewedJobs(long id, Job job, Applicant applicant, LocalDate dateViewed) {
		super();
		this.id = id;
		this.job = job;
		this.applicant = applicant;
		this.dateViewed = dateViewed;
	}
	
	public ViewedJobs(LocalDate dateViewed) {
		super();
		this.dateViewed = dateViewed;
	}

	public ViewedJobs(Job job, Applicant applicant, LocalDate dateViewed) {
		super();
		this.job = job;
		this.applicant = applicant;
		this.dateViewed = dateViewed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public LocalDate getDateViewed() {
		return dateViewed;
	}

	public void setDateViewed(LocalDate dateViewed) {
		this.dateViewed = dateViewed;
	}
	
	
	
	

}

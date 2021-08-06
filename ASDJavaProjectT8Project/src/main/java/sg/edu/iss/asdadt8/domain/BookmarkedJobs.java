package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class BookmarkedJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JsonBackReference
	private Job job;
	
	@ManyToOne
	@JsonBackReference
	private Applicant applicant;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate bookmarkDate;

	public BookmarkedJobs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookmarkedJobs(LocalDate bookmarkDate) {
		super();
		this.bookmarkDate = bookmarkDate;
	}

	public BookmarkedJobs(long id, Job job, Applicant applicant, LocalDate bookmarkDate) {
		super();
		this.id = id;
		this.job = job;
		this.applicant = applicant;
		this.bookmarkDate = bookmarkDate;
	}

	public BookmarkedJobs(Job job, Applicant applicant, LocalDate bookmarkDate) {
		super();
		this.job = job;
		this.applicant = applicant;
		this.bookmarkDate = bookmarkDate;
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

	public LocalDate getBookmarkDate() {
		return bookmarkDate;
	}

	public void setBookmarkDate(LocalDate bookmarkDate) {
		this.bookmarkDate = bookmarkDate;
	}
}

package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

public class BookmarkedJobs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Job job;
	
	@ManyToOne
	private Applicant applicant;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate bookmarkDate;

	public BookmarkedJobs() {
		super();
		// TODO Auto-generated constructor stub
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

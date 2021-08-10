package sg.edu.iss.asdadt8.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("Applicant")
public class Applicant extends User {	
	
	private String resumeURl;
	
	private String userStatus;
	
	private String chatstatus;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate dob;
	
	private String gender;
	
	private String selfIntroduction;
	
	@OneToMany(mappedBy="applicant", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ViewedJobs> viewedJobs = new ArrayList<>();
	
	@ManyToMany(mappedBy="applicants", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SocialGroup> socialGroups = new ArrayList<>();
	
	@OneToMany(mappedBy="applicant", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BookmarkedJobs> bookmarkedJobs = new ArrayList<>();
	
	@OneToOne (mappedBy="applicant")
	@JsonManagedReference
	private Resume resume;
	
	@OneToMany (mappedBy="applicant", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Review> reviews;

	
	public Applicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Applicant(String resumeURl, String userStatus, String chatstatus, LocalDate dob, String gender,
			String selfIntroduction) {
		super();
		this.resumeURl = resumeURl;
		this.userStatus = userStatus;
		this.chatstatus = chatstatus;
		this.dob = dob;
		this.gender = gender;
		this.selfIntroduction = selfIntroduction;
	}

	public Applicant(String resumeURl, String userStatus, String chatstatus, LocalDate dob, String gender,
			String selfIntroduction, List<ViewedJobs> viewedJobs, List<SocialGroup> socialGroups,
			List<BookmarkedJobs> bookmarkedJobs, Resume resume, List<Review> reviews) {
		super();
		this.resumeURl = resumeURl;
		this.userStatus = userStatus;
		this.chatstatus = chatstatus;
		this.dob = dob;
		this.gender = gender;
		this.selfIntroduction = selfIntroduction;
		this.viewedJobs = viewedJobs;
		this.socialGroups = socialGroups;
		this.bookmarkedJobs = bookmarkedJobs;
		this.resume = resume;
		this.reviews = reviews;
	}


	public String getResumeURl() {
		return resumeURl;
	}


	public void setResumeURl(String resumeURl) {
		this.resumeURl = resumeURl;
	}


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	public String getChatstatus() {
		return chatstatus;
	}


	public void setChatstatus(String chatstatus) {
		this.chatstatus = chatstatus;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getSelfIntroduction() {
		return selfIntroduction;
	}


	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}


	public List<ViewedJobs> getViewedJobs() {
		return viewedJobs;
	}


	public void setViewedJobs(List<ViewedJobs> viewedJobs) {
		this.viewedJobs = viewedJobs;
	}


	public List<SocialGroup> getSocialGroups() {
		return socialGroups;
	}


	public void setSocialGroups(List<SocialGroup> socialGroups) {
		this.socialGroups = socialGroups;
	}


	public List<BookmarkedJobs> getBookmarkedJobs() {
		return bookmarkedJobs;
	}


	public void setBookmarkedJobs(List<BookmarkedJobs> bookmarkedJobs) {
		this.bookmarkedJobs = bookmarkedJobs;
	}


	public Resume getResume() {
		return resume;
	}


	public void setResume(Resume resume) {
		this.resume = resume;
	}


	public List<Review> getReview() {
		return reviews;
	}

	//change the input parameter type as list<Review> from Review
	public void setReview(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() ;
	}
	
	
	

}

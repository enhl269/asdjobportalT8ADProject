package sg.edu.iss.asdadt8.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;

public interface jaBookmarkRepository extends JpaRepository<BookmarkedJobs,Long>{

	public List<BookmarkedJobs> findAll();
	
	//@Query("SELECT b FROM BookmarkedJobs b where b.applicant_id=:id")
	//public List<BookmarkedJobs> findBookmarkById(@Param("id") Long id);
	
}

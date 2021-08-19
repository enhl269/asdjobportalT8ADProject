package sg.edu.iss.asdadt8.webuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.asdadt8.domain.BookmarkedJobs;
import sg.edu.iss.asdadt8.domain.Review;

public interface BookmarkJobRepositoryWeb extends JpaRepository<BookmarkedJobs,Long>{
	
	@Query("SELECT b FROM BookmarkedJobs b where b.applicant.id=:applicant")
	public List<BookmarkedJobs> findBookmarkByUserID(@Param("applicant") Long applicant);
	
	@Query("SELECT b FROM BookmarkedJobs b where b.applicant.email=:applicant")
	public List<BookmarkedJobs> findBookmarkByUserEmail(@Param("applicant") String applicant);

}

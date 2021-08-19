package sg.edu.iss.asdadt8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.asdadt8.domain.AvatarFile;
@Repository
public interface AvatarFileRepository extends JpaRepository<AvatarFile, String>{

	AvatarFile findByUsername(String username);

}
package sg.edu.iss.asdadt8.filetest;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {
	 @Autowired
	  private FileRepository fileDBRepository;

	  public File store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    File FileDB = new File(fileName, file.getContentType(), file.getBytes());

	    return fileDBRepository.save(FileDB);
	  }

	  public File getFile(String id) {
	    return fileDBRepository.findById(id).get();
	  }
	  
	  public Stream<File> getAllFiles() {
	    return fileDBRepository.findAll().stream();
	  }
	

}

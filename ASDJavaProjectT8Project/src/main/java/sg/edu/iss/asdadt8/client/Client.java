package sg.edu.iss.asdadt8.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {
	public static String sendPostRequest(String url) {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.POST;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity 
			= new HttpEntity<String>(headers);
		ResponseEntity<String> response 
			= client.exchange(url, method,requestEntity,String.class);
		return response.getBody();
	}


}

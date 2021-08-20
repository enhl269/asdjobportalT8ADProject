package sg.edu.iss.asdadt8.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Client {
	
	private static final String url = "http://www.baidu.com";
	
	public static String sendGetRequest() {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.GET;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity 
			= new HttpEntity<String>(headers);
		ResponseEntity<String> response 
			= client.exchange(url, method,requestEntity,String.class);
		System.out.println("create a request to "+url + ", " + response.getStatusCode());
		return response.getBody();
	}


}

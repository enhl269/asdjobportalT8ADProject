package sg.edu.iss.asdadt8;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import sg.edu.iss.asdadt8.client.Client;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class HttpClientTest {
	
	private static MockWebServer server = new MockWebServer();

	@BeforeAll
    public static void initMockServer() throws IOException {
		server.enqueue(new MockResponse()
       		 	.setResponseCode(200)
       	        .setBody("hello"));
		server.start();
   }
	
	@AfterAll
	public static void teardown() throws IOException{
		server.shutdown();
	}
	
	@Test
	public void clientPostTest(){
		String baseUrl = server.url("/hello").toString();
		assertEquals("hello",Client.sendGetRequest());
	}
}

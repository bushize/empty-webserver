import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {

	HttpResponse httpResponse;
	HashMap statusMap;
	
	@Before
	public void SetUp() {
		httpResponse = new HttpResponse();
		statusMap = httpResponse.getStatusMap();
	}
	
	public String getTestResponse(int status) {
		String statusText = statusMap.get(status).toString();
		String responseBody = "<h1>" + status + " " + statusText + "</h1>";
		String responseHeaderStatus = "HTTP/1.1 " + status + " " + statusText + "\r\n";
		
		int contentLength = responseBody.length();
		
		String response = responseHeaderStatus +
			    "Content-Length: " + contentLength + "\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    responseBody;
		return response;
	}
	
	@Test
	public void return404ForNotFoundURL() throws Exception {
		httpResponse.generateResponse("GET", "/404");
		String response = getTestResponse(404);
		
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return200ForExistingURL() throws Exception {
		httpResponse.generateResponse("GET", "/");
		String response = getTestResponse(200);
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return401IfAuthenticatedURL() throws Exception {
		httpResponse.generateResponse("GET", "logs");
		String response = getTestResponse(401);
		assertEquals(response, httpResponse.getResponse());
	}

}

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {

	HttpResponse httpResponse;
	HashMap statusMap;
	
	public String getTestResponse(HashMap statusMap, int status) {
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
		httpResponse = new HttpResponse("GET", "/404");
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 404);
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return200ForExistingURL() throws Exception {
		httpResponse = new HttpResponse("GET", "/");
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,200);
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return401IfAuthenticatedURL() throws Exception {
		httpResponse = new HttpResponse("GET", "/logs");
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,401);
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void returnStatus200IfURLisExisting() throws Exception {
		httpResponse = new HttpResponse("GET", "/");
		assertEquals(200, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus404IfURLisNotExisting() throws Exception {
		httpResponse = new HttpResponse("GET", "/404");
		assertEquals(404, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus401IfAuthRequired() throws Exception {
		httpResponse = new HttpResponse("GET", "/logs");
		assertEquals(401, httpResponse.getStatusCode());
	}
}

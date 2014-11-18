import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {

	HttpResponse httpResponse;
	HashMap statusMap;
	
	public String getTestResponse(HashMap statusMap, int status, String method) {
		String header = "";
		String statusText = statusMap.get(status).toString();
		String responseBody = "=====START RESPONSE=====\r\n<h1>" + status + " " + statusText + "</h1>\r\n=====END_OF_RESPONSE=====\r\n";
		String responseHeaderStatus = "HTTP/1.1 " + status + " " + statusText + "\r\n";
		
		int contentLength = responseBody.length();
		
		if(method == "OPTIONS") {
			header += "ALLOW: GET,HEAD,POST,OPTIONS,PUT\r\n";
		}
		if(status == 401) {
			header += "WWW-Authenticate: Basic realm=\"admin\"\r\n";
		}
		
		
		String response = responseHeaderStatus +
				header +
			    "Content-Length: " + contentLength + "\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    responseBody;
		return response;
	}
	
	@Test
	public void return404ForNotFoundURL() throws Exception {
		httpResponse = new HttpResponse("GET", "/404", "", false);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 404, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return200ForExistingURL() throws Exception {
		httpResponse = new HttpResponse("GET", "/", "", false);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,200, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return401IfAuthenticatedURL() throws Exception {
		httpResponse = new HttpResponse("GET", "/logs", "", false);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,401, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void returnStatus200IfURLisExisting() throws Exception {
		httpResponse = new HttpResponse("GET", "/", "", false);
		assertEquals(200, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus404IfURLisNotExisting() throws Exception {
		httpResponse = new HttpResponse("GET", "/404", "", false);
		assertEquals(404, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus401IfAuthRequired() throws Exception {
		httpResponse = new HttpResponse("GET", "/logs", "", false);
		assertEquals(401, httpResponse.getStatusCode());
	}
	
	@Test
	public void testMethodOptions() throws Exception {
		httpResponse = new HttpResponse("OPTIONS", "/method_options", "", false);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "OPTIONS");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void testPost() throws Exception {
		httpResponse = new HttpResponse("POST", "/forms", "", false);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "");
		assertEquals(response, httpResponse.getResponse());
	}
	
}

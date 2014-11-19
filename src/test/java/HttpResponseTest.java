import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {

	HttpResponse httpResponse;
	HashMap statusMap;
	static SocketHttpServer server;
	static FakeHttpRequest r;
	
	@Before
	public void SetUp() throws IOException {
		if(server == null) {
			server = new SocketHttpServer(0);
			int port = server.getLocalPort();
			r = new FakeHttpRequest(server);
			r.connect("localhost", port);
		}
	}
	
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
		r.setMethod("GET");
		r.setPath("/404");
		r.setAuthorize(false);
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 404, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return200ForExistingURL() throws Exception {
		r.setMethod("GET");
		r.setPath("/");
		r.setAuthorize(false);
		httpResponse = new HttpResponse(r);

		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,200, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void return401IfAuthenticatedURL() throws Exception {
		r.setMethod("GET");
		r.setPath("/logs");
		r.setAuthorize(false);
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap,401, "GET");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void returnStatus200IfURLisExisting() throws Exception {
		r.setMethod("GET");
		r.setPath("/");
		httpResponse = new HttpResponse(r);
		assertEquals(200, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus404IfURLisNotExisting() throws Exception {
		r.setMethod("GET");
		r.setPath("/404");
		httpResponse = new HttpResponse(r);
		assertEquals(404, httpResponse.getStatusCode());
	}

	@Test
	public void returnStatus401IfAuthRequired() throws Exception {
		r.setMethod("GET");
		r.setPath("/logs");
		httpResponse = new HttpResponse(r);
		assertEquals(401, httpResponse.getStatusCode());
	}
	
	@Test
	public void testMethodOptions() throws Exception {
		r.setMethod("OPTIONS");
		r.setPath("/method_options");
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "OPTIONS");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void testPost() throws Exception {
		r.setMethod("POST");
		r.setPath("/forms");
		r.setParameters("abc");
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "");
		assertEquals(response, httpResponse.getResponse());
	}
	
	@Test
	public void testPut() throws Exception {
		r.setMethod("PUT");
		r.setPath("/forms");
		r.setParameters("abc");
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "");
		assertEquals(response, httpResponse.getResponse());
	}
	@Test
	public void testPatch() throws Exception {
		r.setMethod("Patch");
		r.setPath("/patch-content.txt");
		r.setParameters("abc");
		r.setETag("abcdefg");
		httpResponse = new HttpResponse(r);
		statusMap = httpResponse.getStatusMap();
		String response = getTestResponse(statusMap, 200, "");
		assertEquals(response, httpResponse.getResponse());
	}
}

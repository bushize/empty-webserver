import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {

	private HttpResponse httpResponse;
	
	@Before
	public void SetUp() {
		httpResponse = new HttpResponse();
	}
	
	@Test
	public void return404ForNotFoundURL() throws Exception {
		
		assertEquals("HTTP/1.1 404 Not Found\r\n" +
			    "Content-Length: 22\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    "<h1>404 Not Found</h1>", httpResponse.getResponse("GET", "/abc"));
	}

}

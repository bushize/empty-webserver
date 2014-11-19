import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FakeHttpRequestTest {

	public static SocketHttpServer server;
	FakeHttpRequest	request;
	
	@Before
	public void SetUp() throws IOException {
		if(server == null)
			server = new SocketHttpServer(0);
		 request = new FakeHttpRequest(server);
		 int port = server.getLocalPort();
		 request.connect("127.0.0.1", port);
	}
	
	@Test
	public void setMethod() throws Exception {
		request.setMethod("GET");
		assertEquals("GET", request.method());
	}
	
	@Test
	public void setPath() throws Exception {
		request.setPath("/");
		assertEquals("/", request.getUrl());
	}
	
	@Test
	public void setData() throws Exception {
		request.setParameters("My=data");
		assertEquals("My=data", request.getPamameters());
	}
	
	@Test
	public void deleteData() throws Exception {
		request.setParameters("My=data");
		assertEquals("My=data", request.getPamameters());
		request.deleteParameters("My=data");
		assertEquals(null, request.getPamameters());
	}
	
	@Test
	public void getLogs() throws Exception {
		request.setMethod("GET");
		request.setPath("/");
		request.fireRequest();
		Logger logger = server.getLogger();
		assertEquals(true, logger.getLogs().contains("GET / HTTP/1.1"));
	}
}

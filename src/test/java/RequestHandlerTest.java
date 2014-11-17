import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RequestHandlerTest {
	
	FakeHttpRequest r;
	SocketHttpServer server;
	RequestHandler handler;
	
	@Before
	public void SetUp() throws IOException {
		if(server == null)
			server = new SocketHttpServer(0);
		r = new FakeHttpRequest(server);
		r.connect("127.0.0.1", server.getLocalPort());
		r.setPath(".");
	}
	
	@Test
	public void sendRequest() throws Exception {
		r.setMethod("GET");
		handler = new RequestHandler(r);
		assertNotEquals(null, handler.getRequest());
	}
	
	@Test
	public void returnGetWhenGet() throws Exception {
		r.setMethod("GET");
		handler = new RequestHandler(r);
		assertEquals("GET", handler.getMethod());
	}

	@Test
	public void returnOptionsWhenOptions() throws Exception {
		r.setMethod("OPTIONS");
		handler = new RequestHandler(r);
		assertEquals("OPTIONS", handler.getMethod());
	}

	@Test
	public void returnDeleteWhenDelete() throws Exception {
		r.setMethod("DELETE");
		handler = new RequestHandler(r);
		assertEquals("DELETE", handler.getMethod());
	}

	@Test
	public void returnPutWhenPut() throws Exception {
		r.setMethod("PUT");
		handler = new RequestHandler(r);
		assertEquals("PUT", handler.getMethod());
	}

	@Test
	public void returnPostWhenPost() throws Exception {
		r.setMethod("POST");
		handler = new RequestHandler(r);
		assertEquals("POST", handler.getMethod());
	}
	
	@Test
	public void getPath() throws Exception {
		r.setMethod("GET");
		handler = new RequestHandler(r);
		assertEquals("/Users/sunny.wang/java_training/cob_spec/public/.", handler.getUrl());
	}
	
	@Test
	public void getCorrectResponse() throws Exception {
		r.setMethod("GET");
		handler = new RequestHandler(r);
		assertTrue(handler.getResponse().contains("200"));
	}
	
	@After
	public void cleanup() throws IOException {
		server.close();
	}

}

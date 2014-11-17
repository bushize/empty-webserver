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
		(new Thread(){
			public void run() {
				if(server == null) {
					try {
						server = new SocketHttpServer(5000);
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		 request = new FakeHttpRequest(server);
		 request.connect("127.0.0.1", 5000);
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
}

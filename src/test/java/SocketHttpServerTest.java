import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SocketHttpServerTest {

	static SocketHttpServer server;
	int port = 0;
	
	@Before
	public void SetUp() throws IOException {
		try{
			System.out.println(server);
			if(server == null)
				server = new SocketHttpServer(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void serverIsAlive() throws Exception {
		assertEquals(true, server.isCreated());
	}
	
	@Test
	public void getListeningPort() throws Exception {
		assertNotEquals(0, server.getLocalPort());
	}
}

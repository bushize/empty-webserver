import static org.junit.Assert.*;
import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServerTest {

	private final String ServerRoot = "/c/training/cob_spec/public";
	private final int PORT = 5000;
	private FakeClient fc;
	private Server s;
	private Socket ClientSocket;
	
	@Before
	public void setUp() throws IOException {
		s = new Server(PORT, ServerRoot);		
		ClientSocket = new Socket("localhost", 5000);
		s.setClientSocket(ClientSocket);
		fc = new FakeClient(ClientSocket);
	}

	@Test
	public void ServerStarts() throws IOException {
		s.begin();
        fc.send200Response();
        assertTrue(s.ServerHealthCheck());
		assertEquals("RUNNABLE",s.ServerStatus());
	}

	@Test
	public void ReturnServerPath() throws Exception {
		assertEquals(ServerRoot,Server.ServerDirectory);
	}

	@Test
	public void ReturnServerPort() throws Exception {
		assertEquals(5000,s.getPort());
	}

	@After
	public void TearDown() throws IOException {
		s.close();
	}
}

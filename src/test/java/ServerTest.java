import static org.junit.Assert.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServerTest {

	private final String ServerRoot = "/c/training/cob_spec/public";
	private final int PORT = 5000;
	private FakeClient fc;
	private Server s;
	
	@Before
	public void setUp() throws IOException {
		s = new Server(PORT, ServerRoot);		
		fc = new FakeClient(new Socket("localhost", 5000));
	}

	@Test
	public void ServerStarts() throws IOException {
		s.begin();        
        fc.send200Response();
        assertTrue(s.ServerHealthCheck());
		assertEquals("RUNNABLE",s.ServerStatus());
	}

	@After
	public void TearDown() throws IOException {
		s.close();
	}
}

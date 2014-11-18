import static org.junit.Assert.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ServerTest {

	private ServerSocket serverSocket;
	private final int PORT = 5000;
	private FakeClient fc;
	private Socket ClientSocket;
	private Server s;
	private String ServerRoot = "/c/training/cob_spec/public";
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
		ClientSocket = new Socket("localhost", 5000);
		fc = new FakeClient(ClientSocket);
	}

	@Test
	public void ServerStarts() throws IOException {

        //client
        fc.send200Response();
        
		//server
		s = new Server(PORT,ServerRoot);
		s.begin();
		//ClientSocket = serverSocket.accept();
		//Thread process = new Process(ClientSocket, "/c/training/cob_spec/public");
		//process.start();

        assertTrue(s.ServerHealthCheck());
	}

	@After
	public void TearDown() throws IOException {
		s.Close();
	}
}

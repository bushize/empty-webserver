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
	private Socket clientsocket;
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
		clientsocket = new Socket("localhost", 5000);
		fc = new FakeClient(clientsocket);
	}

	@Test
	public void ServerStarts() throws IOException {

        //client
        fc.send200Response();
        
		//server
		clientsocket = serverSocket.accept();
		Thread process = new Process(clientsocket, "/c/training/cob_spec/public");
		process.start();

        assertTrue(process.isAlive());
	}

	@After
	public void TearDown() throws IOException {
		serverSocket.close();
	}
}

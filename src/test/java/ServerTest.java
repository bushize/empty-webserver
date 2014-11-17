import static org.junit.Assert.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;


public class ServerTest {
/*
	private ServerSocket serverSocket;
	private final int PORT = 5000;
	private FakeClient fc;
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}


	@Test
	public void ServerStarts() throws IOException {

        //client   
        fc = new FakeClient(new Socket("localhost", 5000));
        fc.sendHandShake();
        
		//server
		Socket socket = serverSocket.accept();

        Thread process = new Process(socket, "/c/training/cob_spec/public");
        process.start();

        fc.closeConnection();
        assertTrue(process.isAlive());

	}
*/
}

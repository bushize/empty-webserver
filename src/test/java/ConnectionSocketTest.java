import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionSocketTest {


	@Test
	public void ChecktheSocketStateAfterClosed() throws IOException {

		//fake server
		ServerSocket serverSocket = new ServerSocket(5001);

		ConnectionSocket cs = new ConnectionSocket(new Socket("localhost", 5001));
		cs.end();
		serverSocket.close();
		assertTrue(cs.ClosedState());

	}


	

}

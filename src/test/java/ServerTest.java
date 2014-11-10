

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

public class ServerTest {

	@Test
	public void connectToPort5000() throws Exception {
		Server s = new Server(new ServerSocket(5000));
		assertEquals(5000, s.getPort());		
	}

}

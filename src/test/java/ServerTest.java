import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;


public class ServerTest {
	
	private ServerSocket serverSocket;
	private final int PORT = 5000;
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}

}

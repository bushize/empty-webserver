import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;


public class ServerTest {
	
	private ServerSocket serverSocket;
	private final int PORT = 5000;
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}
}

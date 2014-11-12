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
	private FakeClient fc;
	
	@Before
	public void setUp() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}

	@Test
	public void checkResponse() throws IOException {

        //client   
        fc = new FakeClient(new Socket("localhost", 5000));
        fc.sendHandShake();
        
		//server
		Socket socket = serverSocket.accept();
        Thread process = new ProcessThread(socket, "/c/training/cob_spec/public");
        process.start();	
        
        String 	response = "HTTP/1.1 404 Not Found\r\n" +
			    "Content-Length: 0\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    "<h1>404 Not Found</h1>";
        
        //assertEquals(response, ((ProcessThread) process).getResponse("GET", "/abc"));
            
	}


}

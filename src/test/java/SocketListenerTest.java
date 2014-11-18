import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;


public class SocketListenerTest {

    private ServerSocket serverSocket;
    private int PORT = 5000;
    private FakeClient fc;
    private SocketListener sl;
    private Socket clientsocket;
    private String ServerRoot = "/c/training/cob_spec/public";

    @Before
    public void setUp() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientsocket = new Socket("localhost", 5000);
        fc = new FakeClient(clientsocket);
    }

    @Test
    public void returnInputFromClient() throws IOException {
        //client
        fc.send200Response();

        //server
        clientsocket = serverSocket.accept();
        Thread process = new Process(clientsocket, ServerRoot);
        process.start();
   
        sl = new SocketListener(clientsocket, ServerRoot);
        sl.listen();

        assertEquals("GET / HTTP/1.1", sl.getSocketInput());
    }

    @Test
    public void returnHTTPHeader() throws IOException {

        //client
        fc.send200Response();

        //server
        clientsocket = serverSocket.accept();
        Thread process = new Process(clientsocket, ServerRoot);
        process.start();
        sl = new SocketListener(clientsocket, ServerRoot);
        sl.listen();

        assertEquals("GET",sl.getRequest().getMethod());
        assertEquals("/",sl.getRequest().getPath());

    }

    @After
    public void TearDown() throws IOException {
       serverSocket.close();
    }

}

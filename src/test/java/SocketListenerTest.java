import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertEquals;


public class SocketListenerTest {

    private final String ServerRoot = "c:/training/cob_spec/public";
    private final int PORT = 5000;
    private FakeClient fc;
    private Server s;
    private SocketListener sl;
    private Socket ClientSocket;

    @Before
    public void setUp() throws IOException {
        s = new Server(PORT, ServerRoot);
        ClientSocket = new Socket("localhost", 5000);
        fc = new FakeClient(ClientSocket);
        s.setClientSocket(ClientSocket);
    }

    @Test
    public void returnInputFromClient() throws IOException {
        //server
        s.begin();

        //client
        fc.send200Response();

        sl = new SocketListener(ClientSocket, ServerRoot);
        sl.listen();

        assertEquals("GET / HTTP/1.1", sl.getSocketInput());
    }

    @Test
    public void returnHTTPHeader() throws IOException {

        //server
        s.begin();

        //client
        fc.send200Response();

        sl = new SocketListener(ClientSocket, ServerRoot);
        sl.listen();

        assertEquals("GET",sl.getRequest().getMethod());
        assertEquals("/",sl.getRequest().getPath());
    }

    @After
    public void TearDown() throws IOException {
       s.close();
    }

}

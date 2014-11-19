import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;


public class SocketListenerTest {

    private final String ServerRoot = "c:/training/cob_spec/public";
    private final int PORT = 5000;
    private FakeClient fc;
    private SocketListener sl;
    private ServerSocket ss;

    @Before
    public void setUp() throws IOException {
    	ss = new ServerSocket(PORT);
    	fc = new FakeClient(new Socket("localhost", 5000));
    }
    
    @Test
    public void returnInputFromClient() throws IOException {
           	
        fc.send200Response();
        Socket s = ss.accept();       
        sl = new SocketListener(s, ServerRoot);        
        sl.listen();

        assertEquals("GET / HTTP/1.1", sl.getSocketInput());            
    }

    @Test
    public void returnHTTPHeader() throws IOException {
        
        fc.send200Response();
        Socket s = ss.accept();    
        sl = new SocketListener(s, ServerRoot);
        sl.listen();

        assertEquals("GET", sl.getRequest().getMethod());
        assertEquals("/", sl.getRequest().getPath());
    }

    @After
    public void TearDown() throws IOException {
       ss.close();
    }

}

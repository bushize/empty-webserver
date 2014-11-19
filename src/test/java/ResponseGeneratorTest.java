import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseGeneratorTest {

    private ResponseGenerator response;
    private RequestObject request;

    @Before
    public void setUp() throws Exception {
        request = new RequestObject();
    }

    @Test
    public void testGetHeaders() throws Exception {
        request.setMethod("GET");
        request.setPath("/404");
        request.setDirectory("/");
        response = new ResponseGenerator(request);
        response.getHeaders();
        assertEquals("HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: null\r\n" +
                "\r\n" +
                "<h1>404 Not Found</h1>", response.getHeaders());
    }

    @Test
    public void testGetContent() throws Exception {

    }

    @Test
    public void testGetStatusCode() throws Exception {
        request.setMethod("GET");
        request.setPath("/404");
        request.setDirectory("/");
        response = new ResponseGenerator(request);
        response.getHeaders();
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testIsDirectory() throws Exception {
        request.setMethod("GET");
        request.setPath("/");
        request.setDirectory(".");
        response = new ResponseGenerator(request);
        response.getHeaders();
        assertEquals(true, response.isDirectory());
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseGeneratorTest {

    private ResponseGenerator response;
    private RequestObject request;
    private final String DIRECTORY = "";

    @Before
    public void setUp() throws Exception {
        request = new RequestObject();
    }

    @Test
    public void testGetHeaders() throws Exception {
        request.setMethod("GET");
        request.setPath("/404");
        response = new ResponseGenerator(request, DIRECTORY);
        response.getHeaders();
        assertEquals("HTTP/1.1 404 Not Found" + System.lineSeparator() +
                "Content-Type: null" + System.lineSeparator(), response.getHeaders());
    }

    @Test
    public void testGetContent() throws Exception {

    }

    @Test
    public void testGetStatusCode() throws Exception {
        request.setMethod("GET");
        request.setPath("/404");
        response = new ResponseGenerator(request, DIRECTORY);
        response.getHeaders();
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void testIsDirectory() throws Exception {
        request.setMethod("GET");
        request.setPath("/");
        response = new ResponseGenerator(request, DIRECTORY);
        response.getHeaders();
        assertEquals(true, response.isDirectory());
    }
}
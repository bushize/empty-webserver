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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetHeaders() throws Exception {

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

    }
}
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseGeneratorTest {

    private ResponseGenerator generator;
    private RequestObject requestObj;
    private final String DIRECTORY = "/Users/johnuba/Sample/java/cob_spec/public";

    @Before
    public void setUp() throws Exception {
        requestObj = new RequestObject();
    }

    @Test
    public void testGetContent() throws Exception {
    	requestObj.setMethod("GET");
        requestObj.setFile("/image.jpeg");
    	generator = new ResponseGenerator(requestObj, DIRECTORY);
    	
    	assertTrue(!generator.getContent().toString().equals(null));
    }

    @Test
    public void testFileIsFound() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setFile("/image.jpeg");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getHeaders();
        
        assertEquals(200, generator.getStatusCode());
    }
    
    @Test
    public void testFileNotFound() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setFile("/image.asdsad");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getHeaders();
        
        assertEquals(404, generator.getStatusCode());
    }    

    @Test
    public void testIsDirectory() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setFile("/");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getHeaders();
        assertEquals(true, generator.isDirectory());
    }
}
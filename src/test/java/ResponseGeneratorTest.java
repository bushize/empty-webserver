import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseGeneratorTest {

    private ResponseGenerator generator;
    private RequestObject requestObj;
    private String DIRECTORY = System.getProperty("user.dir");

    @Before
    public void setUp() throws Exception {
        requestObj = new RequestObject();
        DIRECTORY = DIRECTORY + "/src/test/files";
    }

    @Test
    public void testGetContent() throws Exception {
    	requestObj.setMethod("GET");
        requestObj.setPath("/blank.gif");
    	generator = new ResponseGenerator(requestObj, DIRECTORY);
    	
    	assertTrue(generator.getContent().length !=0 );
        assertFalse(generator.getResponse().contains("<html>") );
    }

    @Test
    public void testFileIsFound() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/blank.gif");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        
        assertEquals(200, generator.getStatusCode());
    }

    @Test
    public void testListAllContent() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();

        assertTrue(generator.getResponse().contains("blank.gif"));
    }

    @Test
    public void tesGetParentDirectoryName() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        assertTrue(generator.getResponse().contains("files"));
    }

    @Test
    public void testFileNotFound() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/blank.asdsad");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        
        assertEquals(404, generator.getStatusCode());
    }    

    @Test
    public void testIsDirectory() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        assertEquals(true, generator.isDirectory());
    }
    
    @Test
    public void testIsNotDirectory() throws Exception {
        requestObj.setMethod("GET");
        requestObj.setPath("/image.jpeg");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        assertEquals(false, generator.isDirectory());
    }

    @Test
    public void testPost405() throws Exception {
        requestObj.setMethod("POST");
        requestObj.setPath("/text-file.txt");
        generator = new ResponseGenerator(requestObj, DIRECTORY);
        generator.getResponse();
        assertEquals(405, generator.getStatusCode());
    }
}
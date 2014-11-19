import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class RequestObjectTest {
	
	private final String DIRECTORY = "/Users/johnuba/Sample/java/cob_spec/public";
    RequestObject request;

    @Before
    public void setUp() {
        request = new RequestObject();
    }

	@Test
	public void MethodIsSet() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertEquals("GET", ro.getMethod());
	}
	
	@Test
	public void PathIsSet() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertEquals("image.jpeg", ro.getPath());
	}	
	
	@Test
	public void DirectoryIsSet() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertEquals(DIRECTORY, ro.getDirectory());
	}	
	
	@Test
	public void MethodIsWrong() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertNotEquals("image.jpeg", ro.getMethod());
	}
	
	@Test
	public void PathIsWrong() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertNotEquals("awds", ro.getPath());
	}
	
	@Test
	public void DirectoryIsWrong() {
		RequestObject ro = new RequestObject("GET", "image.jpeg", DIRECTORY);
		assertNotEquals("asdasd", ro.getDirectory());
	}

    @Test
    public void testSetPath() {
        request.setPath("/");
        assertEquals("/", request.getPath());
    }

    @Test
    public void testSetMethod() {
        request.setMethod("GET");
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void testSetDirectory() {
        request.setDirectory("/public");
        assertEquals("/public", request.getDirectory());
    }
}

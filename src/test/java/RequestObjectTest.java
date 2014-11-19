import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class RequestObjectTest {
	
    private RequestObject request;

    @Before
    public void setUp() {
        request = new RequestObject();
    }

	@Test
	public void MethodIsSet() {
		RequestObject ro = new RequestObject("GET", "image.jpeg");
		assertEquals("GET", ro.getMethod());
	}
	
	@Test
	public void PathIsSet() {
		RequestObject ro = new RequestObject("GET", "image.jpeg");
		assertEquals("image.jpeg", ro.getPath());
	}	
	
	@Test
	public void MethodIsWrong() {
		RequestObject ro = new RequestObject("GET", "image.jpeg");
		assertNotEquals("image.jpeg", ro.getMethod());
	}
	
	@Test
	public void PathIsWrong() {
		RequestObject ro = new RequestObject("GET", "image.jpeg");
		assertNotEquals("awds", ro.getPath());
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

}

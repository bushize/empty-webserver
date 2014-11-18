import static org.junit.Assert.*;

import org.junit.Test;


public class RequestObjectTest {
	
	private final String DIRECTORY = "/Users/johnuba/Sample/java/cob_spec/public";

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

}

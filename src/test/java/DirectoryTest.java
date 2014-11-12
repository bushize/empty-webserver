import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DirectoryTest {
	Directory dir;
	
	@Before
	public void SetUp() {
		 dir = new Directory();
	}
	
	@Test
	public void rootShouldExist() throws Exception {		
		String file = "/";
		assertEquals(true, dir.isExisting(file));
	}
	
	@Test
	public void rootShouldBeDirectory() throws Exception {
		String file = "/etc";
		assertEquals(true, dir.isDirectory(file));
	}

}

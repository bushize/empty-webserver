import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class FileSystemTest {
	FileSystem dir;
	String file = "/";
	
	@Before
	public void SetUp() {
		 dir = new FileSystem();
	}
	
	@Test
	public void rootShouldExist() throws Exception {		
		assertEquals(true, dir.isExisting(file));
	}
	
	@Test
	public void rootShouldBeADirectory() throws Exception {
		assertEquals(true, dir.isDirectory(file));
	}

	@Test
	public void returnFilesList() throws Exception {		
		File d = new File(file);
		assertEquals(d.list().length, dir.getFiles(file).length);
	}
	
	@Test
	public void shouldNotReturnHiddenFile() throws Exception {
		
	}

}

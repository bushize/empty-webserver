import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class LoggerTest {

	Logger logger;
	
	@Before
	public void SetUp() throws IOException {
		logger= new Logger();
	}
	
	@Test
	public void appendLog() throws IOException {
		logger.log("GET /form HTTP/1.1");
		assertEquals(true, logger.getLogs().contains("GET /form HTTP/1.1"));
	}
	
	@Test
	public void logTheSecondRequest() throws IOException {
		logger.log("GET /log HTTP/1.1");
		assertEquals(true, logger.getLogs().contains("GET /log HTTP/1.1"));
	}

}

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseGenerator {
	
	private int statusCode;
	private String statusText;
	private String contentType;
	private boolean isDirectory = false;
	private String directory;
	
	private RequestObject requestObject;

	public ResponseGenerator(RequestObject requestObject, String directory) {
		this.requestObject = requestObject;
		this.directory = directory;
	}

	public String getHeaders() {
				
		if (requestObject.getMethod().equals("GET"))
			get();
		else if (requestObject.getMethod().equals("POST"))
			post();

		String response = "";
		response += "HTTP/1.1 " + statusCode + " " +  statusText + "\r\n";
		response += "Content-Type: " + contentType + "\r\n\r\n";
		response += "<h1>" + statusCode + " " + statusText + "</h1>";
			
		return response;
	}
	
	public byte[] getContent() throws IOException {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] content = new byte [4096];
		int i = 0;
		
		InputStream inputStream = new FileInputStream(directory + requestObject.getPath());
		
		while ( (i = inputStream.read(content) ) > 0 ) {
			bos.write(content, 0 ,i);
		}
		
		inputStream.close();
		
		return bos.toByteArray();
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public boolean isDirectory() {
		return isDirectory;
	}
	
	/** HELPER METHODS **/
	
	private void get() {
		if (checkFile()) {
			statusCode = 200;
			statusText = "OK";
		}
		else {					
			statusCode = 404;
			statusText = "Not Found";
		}		
	}
	
	private void post() {
		
	}
	
	private boolean checkFile() {
		
		String fileName = directory + requestObject.getPath();
	
        //if (!fileName.startsWith("/"))
        //   fileName = "/" + fileName;
        
		File f = new File(fileName);
		
		if (f.exists() && !f.isDirectory() ) {
			if (fileName.endsWith(".html") || fileName.endsWith(".htm"))
				contentType = "text/html";
			else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
				contentType = "image/jpeg";
			else if (fileName.endsWith(".gif"))
				contentType = "image/gif";
			else if (fileName.endsWith(".png"))
				contentType = "image/png";
			else
				contentType = "text/plain";
			
			return true;
		}
		else if (f.exists() && f.isDirectory()) {
			isDirectory = true;
			return true;
		}
		else 
			return false;
			
	}
}

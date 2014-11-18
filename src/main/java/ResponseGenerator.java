import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseGenerator {
	
	private String statusCode;
	private String statusText;
	private String contentType;
	private boolean isDirectory = false;
	
	private RequestObject requestObject;

	public ResponseGenerator(RequestObject requestObject) {
		this.requestObject = requestObject;
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
		
		InputStream inputStream = new FileInputStream(requestObject.getDirectory() + requestObject.getPath());
		
		while ( (i = inputStream.read(content) ) > 0 ) {
			bos.write(content, 0 ,i);
		}
		
		inputStream.close();
		
		return bos.toByteArray();
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public boolean checkIfDirectory() {
		return isDirectory;
	}
	
	/** HELPER METHODS **/
	
	private void get() {
		if (checkFile()) {
			statusCode = "200";
			statusText = "OK";
		}
		else {
			
			System.out.println("GET FAILED>>>");
			
			statusCode = "404";
			statusText = "Not Found";
		}		
	}
	
	private void post() {
		
	}
	
	private boolean checkFile() {
		
		String fileName = requestObject.getDirectory() + requestObject.getPath();
	
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
			System.out.println("DIRECTORY EXIST");
			return true;
		}
		else 
			return false;
			
	}
}

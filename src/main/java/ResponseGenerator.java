import java.io.File;

public class ResponseGenerator {
	
	private String statusCode;
	private String statusText;
	private String contentType;
	
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
	
	public byte[] generateContent() {
		
		return null;
	}
	
	/** HELPER METHODS **/
	
	private void get() {
		if (checkFile()) {
			statusCode = "200";
			statusText = "OK";
		}
		else {
			statusCode = "404";
			statusText = "Not Found";
		}		
	}
	
	private void post() {
		
	}
	
	private boolean checkFile() {
		
		String fileName = requestObject.getPath();
		
        if (!fileName.startsWith("/"))
           fileName = "/" + fileName;
        
		File f = new File(fileName);
		
		if (!f.exists())
			return false;
		else if (!f.isDirectory())
			return false;
		
		if (fileName.endsWith(".html") || fileName.endsWith(".htm"))
			contentType = "text/html";
		else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
			contentType = "image/jpeg";
		else if (fileName.endsWith(".gif"))
			contentType = "image/gif";	
		else
			contentType = "text/plain";
		
		return true;		
	}
}

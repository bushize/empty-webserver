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
	private String[] files;
	private String parentFolder;
    private String body;
    private boolean hasContent = false;

	private RequestObject requestObject;

	public ResponseGenerator(RequestObject requestObject, String directory) {
		this.requestObject = requestObject;
		this.directory = directory;
        statusCode = 200;
	}

	public String getHeader() throws IOException {
				
		if (requestObject.getMethod().equals("GET"))
			get();
		else if (requestObject.getMethod().equals("POST"))
			post();
		else if (requestObject.getMethod().equals("PUT"))
			put();
		else if (requestObject.getMethod().equals("DELETE"))
			delete();
		else if (requestObject.getMethod().equals("OPTIONS"))
			options();	
		else
			notAllowed();

		String response = "";
		response = ResponseHeader(response);
		if (isDirectory) {
			response = ResponseBody(response);
		}

		return response;
	}

	private String ResponseHeader(String response) {
		response += String.format("HTTP/1.1 %d %s%n", statusCode, statusText);
		response += String.format("Content-Type: %s%n", contentType);
        if(statusCode == 302) {
            response += "Location:http://localhost:5000/\n";
        } else if(requestObject.getMethod().equals("OPTIONS")) {
            response += "ALLOW: GET,HEAD,POST,OPTIONS,PUT\n";
        }
		return response;
	}

	private String ResponseBody(String response) {
		//Construct the message body
		response += String.format("%n%n");
		response += String.format("<html><head></head><body>");
		if (files != null) {
			response += String.format("Folder:" + parentFolder + "<br>");
			for (String i : files) {
				response += String.format("<a href=\"/" + i + "\">" + i + "</a><br>" );
			}
		}
		response += String.format("</body></html>");
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
        if(requestObject.getPath().equals("/redirect")) {
            statusCode = 302;
            //header = getHeader();
        }
        else if(requestObject.getPath().equals("/logs")) {
            statusCode = 401;
            //header = getHeader();
        }
        else if (requestObject.getPath().equals("/parameters")) {
            UrlQueryStringDecode urlDecoder = new UrlQueryStringDecode(requestObject.getPath());
            String formattedQueryStrings = urlDecoder.getFormattedQueryStringPairs();
            body = formattedQueryStrings;
        }
		else if (checkFile()) {
			statusText = "OK";
		}
		else {					
			statusCode = 404;
			statusText = "Not Found";
		}		
	}
	
	private void post() {
		if(requestObject.getPath().equals("/text-file.txt")) {
            statusCode = 405;
        }
	}
	
	private void put() {
        if(requestObject.getPath().equals("/file1")) {
            statusCode = 405;
        }
	}
	
	private void delete() {
		
	}
	
	private void options() {

	}
	
	private void notAllowed() {
		
	}
	
	private boolean checkFile() {
		
		String fileName = directory + requestObject.getPath();
		File f = new File(fileName);
		
		if (f.exists() && !f.isDirectory() ) {
            hasContent = true;
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
			parentFolder = f.getName();
			contentType = "text/html";
			files = listDirectory(directory);
			return true;
		}
		else
			contentType = "text/plain";
			return false;
			
	}

	private String[] listDirectory(String ServerDirectory){

		File f = null;
		String[] files;

		f = new File(ServerDirectory);

		files = f.list();

		return files;

	}

    public boolean isHasContent() {
        return hasContent;
    }
}

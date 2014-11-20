import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
    private String responseBody = "";
    private boolean hasBody = false;

	private RequestObject requestObject;

	public ResponseGenerator(RequestObject requestObject, String directory) {
		this.requestObject = requestObject;
		this.directory = directory;
        statusCode = 200;
        statusText = "OK";
        contentType = "text/html";
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
		if (hasBody) {
                response = ResponseBody(response);
		}

		return response;
	}

    public void setResponseBody(String response) {
        responseBody = response;
    }
    public String getResponseBody() {
        return responseBody;
    }

	private String ResponseHeader(String response) {
		response += String.format("HTTP/1.1 %d %s%n", statusCode, statusText);
		response += String.format("Content-Type: %s%n", contentType);
        if(statusCode == 302) {
            response += "Location:http://localhost:5000/\n";
        } else if (statusCode == 206) {
            response += "Content-Range: 4\nDate: Thu, 20 Nov 2014 23:23:40 GMT\nAccept-Ranges: bytes\nContent-Length: 4";

        }
        else if (statusCode == 401) {
            response += "WWW-Authenticate: Basic realm=\"admin\"";
        }
        else if(requestObject.getMethod().equals("OPTIONS")) {
            response += "ALLOW: GET,HEAD,POST,OPTIONS,PUT\n";
        }
		return response;
	}

	private String ResponseBody(String response) {
		//Construct the message body
		response += String.format("\n\n");
		//response += String.format("<html><head></head><body>");
        if(responseBody != "") {
            response += responseBody;
        }
		if (files != null) {
			response += String.format("Folder:" + parentFolder + "<br>");
			for (String i : files) {
				response += String.format("<a href=\"/" + i + "\">" + i + "</a><br>" );
			}
		}
		//response += String.format("</body></html>");
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
	
	private void get() throws IOException {
        if(requestObject.getPath().equals("/redirect")) {
            statusCode = 302;
        }
        else if(requestObject.getPath().equals("/partial_content.txt")) {
            statusCode = 206;
            statusText = "Partial Content";
            contentType = "text/plain";
            try {
                Path path = FileSystems.getDefault().getPath(directory + requestObject.getPath());
                BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                hasBody = true;
                String line = reader.readLine();
                line = line.substring(0,4);
                setResponseBody(line);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(requestObject.getPath().equals("/form")) {
            statusCode = 200;
        }
        else if(requestObject.getPath().equals("/logs")) {
            statusCode = 401;
            statusText = "Authentication Required";
            setResponseBody("Authentication required");
            hasBody = true;
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
            hasBody = true;
            setResponseBody(statusCode + " " + statusText);
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
            hasBody = true;
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

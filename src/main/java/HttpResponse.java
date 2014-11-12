import java.io.File;


public class HttpResponse {

	private String response;
	private int statusCode;
	private String httpMethod;
	private String url;
	
	public String getResponse(String httpMethod, String url) {
		this.httpMethod = httpMethod;
		this.url = url;
		
		if (httpMethod.equals("GET")) {
			if(checkURL(url)) {
				statusCode = 200;
			} else {
				statusCode = 404;
			}
		} else if (httpMethod.equals("POST") || httpMethod.equals("PUT") 
				|| httpMethod.equals("DELETE")) {
			statusCode = 200;
		} else if(httpMethod.equals("OPTIONS")) {			
			statusCode = 200;
		} else {
			statusCode = 200;
		}
		
		
		String statusText;;
		switch(statusCode) {
		case 404:
			statusText = "Not Found";
			break;
		case 400:
			statusText = "Bad Request";
			break;
		case 500:
			statusText = "Interal Error";
			break;
		default:
			statusText = "OK";
		}
		String responseBody = "<h1>" + statusCode + " " + statusText + "</h1>";
		String responseHeaderStatus = "HTTP/1.1 " + statusCode + " " + statusText + "\r\n";
		
		int contentLength = responseBody.length();
		
		this.response = responseHeaderStatus +
			    "Content-Length: " + contentLength + "\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    responseBody;
		return this.response;
	}

	public int getStatusCode() {
		return this.statusCode;
	}
	
	private boolean checkURL(String p) {
		
		String fileName = p;
        if (!fileName.startsWith("/"))
           fileName = "/" + fileName;
        
		File f = new File(fileName);
		
		if (!f.exists())
			return false;
		
		return true;
	}

}

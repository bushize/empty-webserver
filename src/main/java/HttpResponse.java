import java.io.File;
import java.util.HashMap;


public class HttpResponse {

	private String response;
	private int statusCode;
	private String requestMethod;
	private String url;
	private String lastPostData;
	private String header = "";
	private boolean isAuthorized;
	public final HashMap<Integer, String> statusMap = new HashMap<Integer, String>();
	
	public HttpResponse(String httpMethod, String url, String lastPost, boolean isAuthorized) {
		statusMap.put(404, "Not Found");
		statusMap.put(401, "Authentication required");
		statusMap.put(200, "OK");
		this.requestMethod = httpMethod;
		this.url = url;
		this.lastPostData = lastPost;
		this.isAuthorized = isAuthorized;
		this.generateResponse();
	}
	
	public void generateResponse() {
		String responseBody = "=====START RESPONSE=====\r\n";
		statusCode = 200;
		if (requestMethod.equals("GET")) {
			if(url.contains("redirect")) {
				statusCode = 302;
				header += "Location: http://localhost:5000/\r\n";
			} else if(url.contains("logs")) {
				if(!isAuthorized) {
					header += "WWW-Authenticate: Basic realm=\"admin\"\r\n";
					statusCode = 401;
				} else {
					responseBody += getLogs();
				}
			} else if(url.contains("form")) {
				if(this.lastPostData != null) {
					responseBody += this.lastPostData + "\r\n";
				}
			} else if(!checkURL(url)) {
				statusCode = 404;
			}
		} else if(requestMethod.equals("PUT")) {
			if(url.contains("file1")) {
				statusCode = 405;
			}
		} else if (requestMethod.equals("POST")) {
			if(url.contains("text-file.txt")) {
				statusCode = 405;
			}
		} else if(requestMethod.equals("OPTIONS")) {		
			header += "ALLOW: GET,HEAD,POST,OPTIONS,PUT\r\n";
		}
		
		String statusText = statusMap.get(statusCode);
		responseBody += ("<h1>" + statusCode + " " + statusText + "</h1>\r\n" +
						"=====END_OF_RESPONSE=====\r\n");
		String responseHeaderStatus = "HTTP/1.1 " + statusCode + " " + statusText + "\r\n";
		
		int contentLength = responseBody.length();
		
		this.response = responseHeaderStatus +
				header +
				"Content-Length: " + contentLength + "\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    responseBody;
	}

	private String getLogs() {
		//TODO add a log
		return "GET /log HTTP/1.1 \r\n" + "PUT /these HTTP/1.1 \r\n" + "HEAD /requests HTTP/1.1\r\n";
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

	public String getResponse() {
		return this.response;
	}

	public HashMap getStatusMap() {
		return statusMap;
	}

}

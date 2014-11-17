
public class RequestHandler {

	private String requestMethod;
	private String requestUrl;
	private HttpServer server;
	private String response;
	private int responseStatusCode;
	HttpResponse httpResponse;
	HttpRequest httpRequest;

	public RequestHandler(HttpRequest r) {
		requestMethod = r.method();
		server = r.getServer();
		httpRequest = r;
		requestUrl = parseUrl(r.getUrl());
		process();
	}

	private String parseUrl(String path) {
		String rootDir = server.getRootDirectory();
		if (path.startsWith("/"))
	           path = rootDir + path;
		else
	           path = rootDir + "/" + path;
		
		return path;
	}

	private void process() {
		httpResponse = new HttpResponse(requestMethod, requestUrl);
		this.response = httpResponse.getResponse();
		this.responseStatusCode = httpResponse.getStatusCode();
	}
	
	public int getResponseStatusCode() {
		return this.responseStatusCode;
	}

	public HttpRequest getRequest() {
		return this.httpRequest;
	}

	public String getMethod() {
		return this.requestMethod;
	}

	public String getUrl() {
		return this.requestUrl;
	}

	public String getResponse() {
		return this.response;
	}

}

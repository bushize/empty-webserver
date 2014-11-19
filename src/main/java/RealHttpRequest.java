import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class RealHttpRequest implements HttpRequest {
	private HttpServer server;
	private Socket client;
	private String url;
	private String requestMethod;
	private int responseStatusCode;
	private String parameters;
	private boolean authorized = false;
	private String eTag;

	public RealHttpRequest(HttpServer server, Socket client) throws IOException {
		this.server = server;
		this.client = client;
		this.getInput();
	}

	public String method() {
		return requestMethod;
	}

	protected void getInput() throws IOException {
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String request = in.readLine();
		
		Logger logger = server.getLogger();
		logger.log(request);
		// System.out.println("Server recieved request from client: " +
		// request);
		if (request == null || request.trim().length() == 0)
			throw new IOException("No request; no incoming data received.");
		// System.out.println(request);

		StringTokenizer tokenizedLine = new StringTokenizer(request);
		this.requestMethod = tokenizedLine.nextToken();

		String path = tokenizedLine.nextToken();
		//String httpVersion = tokenizedLine.nextToken();
		
		int contentLength = 0;
		String header = request;
		String line;
		
//		while((line = in.readLine()) != null) {
//			System.out.println(line);
//		}
        while (!(line = in.readLine()).equals("")) {
            header += "\n" + line;
            if(requestMethod.startsWith("GET") && line.contains("Authorization: Basic")) {
            	this.authorized = true;
            } else if (requestMethod.startsWith("POST") || requestMethod.startsWith("PUT") || requestMethod.startsWith("PATCH")) {
            	if(requestMethod.startsWith("PATCH")) {
            		if (line.startsWith("If-Match:")) {
            			this.eTag = line.replace("If-Match: ", "");
            		}
            	}
                final String contentHeader = "Content-Length: ";
                if (line.startsWith(contentHeader)) {
                    contentLength = Integer.parseInt(line.substring(contentHeader.length()));
                }
            }
        }

        if(requestMethod.startsWith("POST") || requestMethod.startsWith("PUT") || requestMethod.startsWith("PATCH")) {
        	//TODO: post data need to be an array or list
            StringBuilder body = new StringBuilder();
            int c = 0;
            for (int i = 0; i < contentLength; i++) {
                c = in.read();
                body.append((char) c);
            }
            header += "\nData: " + body.toString();
            this.parameters = body.toString();
        }
        //Authorization
        System.out.println(header + "\r\nEND REQUEST\r\n");
        
        if(requestMethod.startsWith("DELETE")) {
        	server.updateLastPostData(null);
        }
        
		this.url = path;
	}

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public String getRequestMethod() {
		return this.requestMethod;
	}

	public String getUrl() {
		return this.url;
	}

	@Override
	public HttpServer getServer() {
		return this.server;
	}
	
	public String getParameters() {
		return this.parameters;
	}
	
	public boolean isAuthorized() {
		return this.authorized;
	}
	
	public String getETag() {
		return this.eTag;
	}
}

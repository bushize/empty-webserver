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

		// System.out.println("Server recieved request from client: " +
		// request);
		if (request == null || request.trim().length() == 0)
			throw new IOException("No request; no incoming data received.");
		// System.out.println(request);

		StringTokenizer tokenizedLine = new StringTokenizer(request);
		this.requestMethod = tokenizedLine.nextToken();

		String path = tokenizedLine.nextToken();
		// String body = tokenizedLine.nextToken();

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
}

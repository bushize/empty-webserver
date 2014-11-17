
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FakeHttpRequest implements HttpRequest {

	private String path;
	private Socket socket;
	private HttpServer server;
	private String response;
	private String method;
	
	public FakeHttpRequest(HttpServer server) throws IOException {
		this.server = server;
		socket = new Socket();
	}
	
	public void connect(String host, int port) throws IOException {
		InetSocketAddress inet = new InetSocketAddress(host, port);
		this.socket.connect(inet);
	}
	
	public void fireRequest() throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String requestStr = this.method + " " + this.path + " HTTP/1.1\r\n";
        InputStream inStream = new ByteArrayInputStream(requestStr.getBytes(StandardCharsets.UTF_8));
        BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
        out.println(input.readLine());
        //generateResponse();
        //input.close();
	}

	public String getResponse() throws IOException {
		/*
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String response = "";
        String input;
        while((input = in.readLine()) != null) {
        	response += input;
        }
        */
		return this.response;
	}
/*
	public void generateResponse() throws IOException {
		RealHttpRequest request = new RealHttpRequest(server, this);
		request.request();
		
		HttpResponse httpResponse = request.getResponse();
		this.response = httpResponse.getResponse(request.getRequestMethod(), request.getUrl());
		this.statusCode = httpResponse.getStatusCode();
	}
*/
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String method() {
		return this.method;
	}

	@Override
	public String getUrl() {
		return this.path;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public HttpServer getServer() {
		return this.server;
	}

}


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketHttpServer implements HttpServer {
	private static final String ROOT_DIRECTORY = "/Users/sunny.wang/java_training/cob_spec/public";
	private static ServerSocket server;
	private String rootDirectory;
	private int statusCode;
	private boolean isCreated = false;
	private int port;
	private String lastPostData;
	private String lastPutData;
	private Logger logger = new Logger();

	public SocketHttpServer(int port) throws IOException {
		server = new ServerSocket(port);
		this.port = server.getLocalPort();
		isCreated = true;
	}

	public boolean isCreated() {
		return isCreated;
	}
	
	public void start() throws IOException {
		while(true) {
	        Socket client = server.accept();
	        new ServerThread(this, client);	        
		}
	}

	public void run(Socket client) throws IOException {
		RealHttpRequest request = new RealHttpRequest(this, client);
		String parameters = request.getParameters();
		if(parameters != null) {
			this.lastPostData = parameters;
		}
		if(request.method() == "DELETE") {
			this.lastPostData = null;
		}
		//System.out.println("SocketHttpServer - lastPostData: " + this.lastPostData);
		RequestHandler handler = new RequestHandler(request);
		this.statusCode = handler.getResponseStatusCode();
		output(client, handler);
        client.close();
	}

	public ServerSocket getServer() {
		return server;
	}

	public int statusCode() {
		return statusCode;
	}

	public void close() throws IOException {
		server.close();
	}

	public void setRootDirectory(String homeDirectory) {
		this.rootDirectory = homeDirectory;
	}
	
	public String getRootDirectory() {
		return this.rootDirectory != null ? this.rootDirectory : ROOT_DIRECTORY;
	}

	@Override
	public void output(Socket client, RequestHandler rh) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		String response = rh.getResponse();
		System.out.println(response);
        out.write(response);
        out.flush();
        out.close();
        client.close();
        //server.close();
	}

	public int getLocalPort() {
		return this.port;
	}
	
	public String getLastPostData() {
		return this.lastPostData;
	}

	public void updateLastPostData(String data) {
		this.lastPostData = data;
	}
	
	public Logger getLogger() {
		return this.logger;
	}

}

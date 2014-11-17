import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public interface HttpServer {
	public boolean isCreated();
	
	public void start() throws IOException;
	public void run(Socket client) throws IOException ;

	public void response(Socket client, RequestHandler requestHandler) throws IOException ;

	public ServerSocket getServer();

	public int statusCode();

	public void close() throws IOException ;

	public void setRootDirectory(String homeDirectory);
	
	public String getRootDirectory();

}

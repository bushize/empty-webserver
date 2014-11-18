import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public interface HttpServer {
	public boolean isCreated();
	
	public void start() throws IOException;
	public void run(Socket client) throws IOException ;

	public void output(Socket client, RequestHandler requestHandler) throws IOException ;

	public ServerSocket getServer();

	public int statusCode();

	public void close() throws IOException ;

	public void setRootDirectory(String homeDirectory);
	public int getLocalPort();
	public String getRootDirectory();
	public String getLastPostData();
	public void updateLastPostData(String data);
	public Logger getLogger();

}

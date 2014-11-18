import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;


public class SocketListener {
	
	private Socket socket;
	private String socketInput;
	private String directory;
	
	public SocketListener(Socket socket, String directory) {
		this.socket = socket;
		this.directory = directory;
	}

	public void listen() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));					
		this.socketInput = in.readLine();
	}
	
	public RequestObject getRequest() {
						
		StringTokenizer tokenizedLine = new StringTokenizer(socketInput);
		String httpMethod = tokenizedLine.nextToken();
		String path = tokenizedLine.nextToken();
		RequestObject ro = new RequestObject(httpMethod, path, directory);
		
		return ro;
	}

    public String getSocketInput() {
        return this.socketInput;
    }
}

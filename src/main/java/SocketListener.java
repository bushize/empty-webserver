import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;


public class SocketListener {
	
	private Socket socket;
	private String socketInput;
	
	public SocketListener(Socket socket) {
		this.socket = socket;
	}

	public String listen() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));					
		socketInput = in.readLine();
		
		return socketInput;		
	}
	
	public RequestObject getRequest() {
						
		StringTokenizer tokenizedLine = new StringTokenizer(socketInput);
		String httpMethod = tokenizedLine.nextToken();
		String path = tokenizedLine.nextToken();
		
		RequestObject ro = new RequestObject(httpMethod, path);
		
		return ro;
	}
}

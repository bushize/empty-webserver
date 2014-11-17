import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class ResponseWriter {
	
	private Socket socket;	
	
	public ResponseWriter(Socket socket) {
		this.socket = socket;
	}
	
	public void writeHeaders(String response) throws IOException {
		PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
		
		System.out.println(response);
		
		out.print(response);
		out.close();
	}
	
	public void writeContent(byte[] arr) {
		
	}
}

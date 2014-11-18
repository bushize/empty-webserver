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
		
		System.out.println(response + "\n");
		
		out.print(response);
	}
	
	public void writeContent(byte[] arr) throws IOException {
		PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
		
		for (int i = 0; i < arr.length; i++) {
			out.write(arr[i]);
		}
	}
}

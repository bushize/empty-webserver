import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class ResponseWriter {
	
	private Socket socket;	
	PrintStream out;
	
	public ResponseWriter(Socket socket) throws IOException {
		this.socket = socket;
		out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public void writeHeaders(String response) throws IOException {
		
		System.out.println(response + "\n");
		
		out.print(response);
	}
	
	public void writeContent(byte[] arr) throws IOException {
		PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
		
		for (int i = 0; i < arr.length; i++) {
			out.write(arr[i]);
		}
	}
	
	public void closeStream() {
		out.close();
	}
}

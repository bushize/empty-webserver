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
		out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));		
		out.write(arr, 0 , arr.length);
		out.flush();		
	}
	
	public void closeStream() {
		out.close();
	}
}

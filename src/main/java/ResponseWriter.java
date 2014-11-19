import java.io.*;
import java.net.Socket;


public class ResponseWriter {
	
	private Socket socket;	
	PrintStream out;
	
	public ResponseWriter(Socket socket) throws IOException {
		this.socket = socket;
		out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public void writeHeaders(String response) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(response);
        writer.flush();
	}
	
	public void writeContent(byte[] arr) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(arr);
		outputStream.flush();
	}
	
	public void closeStream() {
		out.close();
	}
}

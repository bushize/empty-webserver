import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ProcessThread extends Thread {
	private Socket socket;
	
	public ProcessThread(Socket socket){
		this.socket = socket;
	}
	
	public void run() {
		String response = "HTTP/1.1 404 Not Found\r\n" +
			    "Content-Length: 22\r\n" +
			    "Content-Type: text/html\r\n\r\n" +
			    "<h1>404 Not Found</h1>";
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write(response);
			out.flush();	
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
			
	}

}

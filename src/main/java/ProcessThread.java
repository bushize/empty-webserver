import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;


public class ProcessThread extends Thread {
	private Socket socket;
	private String response;
	private String path;
	
	public ProcessThread(Socket socket, String path){
		this.socket = socket;
		this.path = path;
	}
	
	public void run() {
						
		PrintWriter out;
		BufferedReader in;
		
		try {
					
			in = new BufferedReader(
			        new InputStreamReader(socket.getInputStream()));
			
			StringTokenizer tokenizedLine = new StringTokenizer(in.readLine());
			String str = tokenizedLine.nextToken();
			String p = tokenizedLine.nextToken();
			
			System.out.println("RECV:" + str);
			System.out.println("RECV:" + p);
			
			if (str.equals("GET") && pathIsGood(p)) {
				response = "HTTP/1.1 200 OK\r\n" +
					    "Content-Length: 0\r\n" +
					    "Content-Type: text/html\r\n\r\n" +
					    "<h1>200 OK</h1>";				
			}
			else {
				response = "HTTP/1.1 404 Not Found\r\n" +
					    "Content-Length: 0\r\n" +
					    "Content-Type: text/html\r\n\r\n" +
					    "<h1>404 Not Found</h1>"; 
			}
			
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write(response);
			out.flush();	
			socket.close();
			
		} catch (IOException e) {		
			e.printStackTrace();
		}	 
			
	}
	
	public String getResponse() {		
		return response;
	}
	
	private boolean pathIsGood(String p) {
		
		File f = new File(path + p);
		
		if (!f.exists())
			return false;
		
		return true;
	}

}

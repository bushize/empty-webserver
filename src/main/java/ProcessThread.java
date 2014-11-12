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
			String httpMethod = tokenizedLine.nextToken();
			String path = tokenizedLine.nextToken();
			
			System.out.println("RECV:" + httpMethod);
			System.out.println("RECV:" + path);
			
			String response200 = "HTTP/1.1 200 OK\r\n" +
				    "Content-Length: 0\r\n" +
				    "Content-Type: text/html\r\n\r\n" +
				    "<h1>200 OK</h1>";
			
			String response404 = "HTTP/1.1 404 Not Found\r\n" +
				    "Content-Length: 0\r\n" +
				    "Content-Type: text/html\r\n\r\n" +
				    "<h1>404 Not Found</h1>";
			
			String response400 = "HTTP/1.1 400 Not Found\r\n" +
				    "Content-Length: 0\r\n" +
				    "Content-Type: text/html\r\n\r\n" +
				    "<h1>400 bad request</h1>";
			
			if (httpMethod.equals("GET")) {
				if(pathIsGood(path)) {
					response = response200;	
				} else {
					response = response404;
				}
			} else if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {
				response = response200;
			} else {
				 response = response400;
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

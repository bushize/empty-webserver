import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ProcessThread extends Thread {
	private Socket socket;
	private String response;
	private String baseurl;
	private ServerSocket serverSocket;
	
	public ProcessThread(Socket socket, String path){
		//this.socket = socket;
		//this.path = path;
	}
	
	public ProcessThread(int port, String path) throws IOException {
		serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		this.baseurl = path;
	}

	public void run() {
						
		PrintWriter out;
		BufferedReader in;
		
		try {					
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
			StringTokenizer tokenizedLine = new StringTokenizer(in.readLine());
			String httpMethod = tokenizedLine.nextToken();
			String path = tokenizedLine.nextToken();
			
			if (path.startsWith("/"))
		           path = baseurl + path;
		        else
		           path = baseurl + "/" + path;
			
			HttpResponse httpResponse = new HttpResponse();
			response = httpResponse.getResponse(httpMethod, path);
			
			System.out.println("RECV:" + httpMethod);
			System.out.println("RECV:" + path);
			System.out.println(response);
			
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write(response);
			out.flush();	
			socket.close();
			
		} catch (IOException e) {		
			e.printStackTrace();
		}	 
			
	}

}

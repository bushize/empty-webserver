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
		this.socket = socket;
		this.baseurl = path;
	}
	
//	public ProcessThread(int port, String path) throws IOException {
//		serverSocket = new ServerSocket(port);
//		socket = serverSocket.accept();
//		this.baseurl = path;
//	}

	public void run() {
						
		PrintWriter out;
		BufferedReader in;
		
		try {					
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));					
			response = getSocketInput(in.readLine());
			
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write(response);
			out.flush();	
			socket.close();
			
		} catch (IOException e) {		
			e.printStackTrace();
		}	 
			
	}

	private String getSocketInput(String readLine) {
		StringTokenizer tokenizedLine = new StringTokenizer(readLine);
		String httpMethod = tokenizedLine.nextToken();
		String path = tokenizedLine.nextToken();
		//String body = tokenizedLine.nextToken();
		
		if (path.startsWith("/"))
	           path = baseurl + path;
		else
	           path = baseurl + "/" + path;
		
		HttpResponse httpResponse = new HttpResponse();
		response = httpResponse.getResponse(httpMethod, path);
		
		System.out.println("METHOD:" + httpMethod);
		System.out.println("PATH:" + path);
		//System.out.println("BODY:" + body);
		System.out.println(response);		
		return response;
	}

}

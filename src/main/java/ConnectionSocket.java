import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;


public class ConnectionSocket {
	
	private Socket socket;


	public ConnectionSocket(Socket socket) {
		this.socket = socket;
	}
		
	public String getInput(String baseurl) throws IOException {
		
		String response;
		BufferedReader in;


		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		response = in.readLine();
		
		StringTokenizer tokenizedLine = new StringTokenizer(response);

		String httpMethod = tokenizedLine.nextToken();
		String path = tokenizedLine.nextToken();
		
		if (path.startsWith("/"))
			path = baseurl + path;
		else
			path = baseurl + "/" + path;
		
		HttpResponse httpResponse = new HttpResponse();
		httpResponse.generateResponse(httpMethod, path);
        response = httpResponse.getResponse();

        /*
        System.out.println("METHOD:" + httpMethod);
		System.out.println("PATH:" + path);
		System.out.println(response);		
		*/
        
		return response;
	}	

	public void writeOutput(String str) throws IOException {
		PrintWriter out;
		out = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("TO WRITE: " + str);
		out.write(str);
		out.flush();				
	}
	
	public void end() throws IOException {
		socket.close();
	}

	public boolean ClosedState(){
		return socket.isClosed();
	}
}

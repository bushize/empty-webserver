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
        String postData = "";

		if (path.startsWith("/"))
			path = baseurl + path;
		else
			path = baseurl + "/" + path;

        String line = "";

        int contentLength = 0;
        while (!(line = in.readLine()).equals("")) {
            //response += "\n" + line;

            if (httpMethod.startsWith("POST")) {
                final String contentHeader = "Content-Length: ";
                if (line.startsWith(contentHeader)) {
                    contentLength = Integer.parseInt(line.substring(contentHeader.length()));
                }
            }
        }

        if(httpMethod.startsWith("POST")) {
            StringBuilder body = new StringBuilder();
            int c = 0;
            for (int i = 0; i < contentLength; i++) {
                c = in.read();
                body.append((char) c);
            }
            response += " " + body.toString();
            postData = body.toString();
        }

        System.out.println(response);
        HttpResponse httpResponse = new HttpResponse();
		httpResponse.generateResponse(httpMethod, path, postData);
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
		System.out.println("\r\nTO WRITE: " + str);
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

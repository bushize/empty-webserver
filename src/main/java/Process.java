import java.net.Socket;

public class Process extends Thread {
	
	private Socket socket;
	private SocketListener socketListener;
	private ResponseGenerator responseGenerator;
	private ResponseWriter responseWriter;
	private String path;
	
	public Process(Socket socket, String path){
		this.socket = socket;
		this.path = path;
	}
	
	public void run() {		
		try 
		{		
			socketListener = new SocketListener(socket);	
			socketListener.listen();
			
			responseGenerator = new ResponseGenerator(socketListener.getRequest());
			
			responseWriter = new ResponseWriter(socket);	
			responseWriter.writeHeaders(responseGenerator.getHeaders());
			//responseWriter.writeContent();
			
			socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
	}

}

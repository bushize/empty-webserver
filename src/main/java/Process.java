import java.net.Socket;

public class Process extends Thread {
	
	private Socket socket;
	private SocketListener socketListener;
	private ResponseGenerator responseGenerator;
	private ResponseWriter responseWriter;
	private String directory;
	
	public Process(Socket socket){
		this.socket = socket;
		//this.directory = directory;
	}
	
	public void run() {		
		try 
		{		
			socketListener = new SocketListener(socket);
			socketListener.listen();
			
			responseGenerator = new ResponseGenerator(socketListener.getRequest());
			
			responseWriter = new ResponseWriter(socket);	
			responseWriter.writeHeaders(responseGenerator.getHeaders());
			if (responseGenerator.getStatusCode() == 200 && !responseGenerator.isDirectory())
				responseWriter.writeContent(responseGenerator.getContent());
			responseWriter.closeStream();
			
			socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
	}

}

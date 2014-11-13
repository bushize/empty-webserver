import java.net.Socket;

public class Process extends Thread {
	
	ConnectionSocket cs;
	private String baseurl;
	
	public Process(Socket socket, String baseurl){
		this.cs = new ConnectionSocket(socket);
		this.baseurl = baseurl;
	}
	
	public void run() {		
		try {
			String toWrite = cs.getInput(baseurl); 
			cs.writeOutput(toWrite); 	
			cs.end();
		}
		catch(Exception e) {
			e.printStackTrace();
		}			
	}

}

import java.io.*;
import java.net.Socket;

public class FakeClient {
	
	private Socket client;

	public FakeClient(Socket socket) {
		this.client = socket;
	}

	public void sendHandShake() throws IOException {

		OutputStream outToServer = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);

		out.writeUTF(client.getRemoteSocketAddress().toString());
	}


	public void closeConnection() throws IOException {
		this.client.close();
	}
}

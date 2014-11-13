import java.io.*;
import java.net.Socket;

public class FakeClient {
	
	private Socket client;

	public FakeClient(Socket socket) {
		this.client = socket;
	}

	public void send200Response() throws IOException {

		OutputStream outToServer = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);
		out.writeUTF("GET / HTTP/1.1 " + client.getRemoteSocketAddress().toString());
	}

	public void send404Response() throws IOException {

		PrintWriter out = new PrintWriter(new BufferedWriter (new OutputStreamWriter(client.getOutputStream())),true);
		out.write("GET /foo HTTP/1.1 " + client.getRemoteSocketAddress().toString());

		System.out.println(out.toString());

		//OutputStream outToServer = client.getOutputStream();
		//OutputStreamWriter out = new OutputStreamWriter(outToServer);
		//DataOutputStream out = new DataOutputStream(outToServer);
		//out.writeUTF("GET /foo HTTP/1.1 " + client.getRemoteSocketAddress().toString());
		//out.writeChars("GET /foo HTTP/1.1 " + client.getRemoteSocketAddress().toString());
		//out.writeUTF("Test /");
		//out.write("GET /foo HTTP/1.1 " + client.getRemoteSocketAddress().toString());
	}

	public void closeConnection() throws IOException {
		this.client.close();
	}
}

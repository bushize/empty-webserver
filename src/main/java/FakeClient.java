import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FakeClient {
	
	private Socket client;

	public FakeClient(Socket socket) {
		this.client = socket;
	}

	public void send200Response() throws IOException {

		PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        String requestStr = "GET / HTTP/1.1\r\n";

        InputStream inStream = new ByteArrayInputStream(requestStr.getBytes(StandardCharsets.UTF_8));

        BufferedReader input = new BufferedReader(new InputStreamReader(inStream));

        out.println(input.readLine());
	}

	public void send404Response() throws IOException {

		PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        String requestStr = "GET /foo HTTP/1.1\r\n";

        InputStream inStream = new ByteArrayInputStream(requestStr.getBytes(StandardCharsets.UTF_8));

        BufferedReader input = new BufferedReader(new InputStreamReader(inStream));

        out.println(input.readLine());

	}

	public void closeConnection() throws IOException {
		this.client.close();
	}
}

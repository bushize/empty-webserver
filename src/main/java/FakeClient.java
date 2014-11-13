import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class FakeClient {
	
	private Socket client;
	String source = "This is the source of my input stream";
	InputStream stream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));


	public FakeClient(Socket socket) {
		this.client = socket;
	}

	public void sendHandShake() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
	}
	

}

import java.util.HashMap;
import java.util.Map;


public class Config {
	private static Map<String, Map> router = new HashMap<String, Map>();
	private Map<String, Map> requestMethod = new HashMap<String, Map>();
	private Map<String, String> urls = new HashMap<String, String>();
	
	public Config() {
		this.urls.put("statusCode", "200");
		this.urls.put("content","default content");
		router.put("/patch-content.txt", urls);
	}
}

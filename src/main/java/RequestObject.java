
public class RequestObject {
	private String method;
	private String path;
	
	public RequestObject(String method, String path) {
		this.method = method;
		this.path = path;
	}

    public RequestObject() {}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}

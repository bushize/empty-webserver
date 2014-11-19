
public class RequestObject {
	private String method;
	private String path;
	private String directory;
	
	public RequestObject(String method, String path, String directory) {
		this.method = method;
		this.path = path;
		this.directory = directory;

        System.out.println("method: " + method + " path: " + path);
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
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getDirectory() {
		return directory;
	}
}

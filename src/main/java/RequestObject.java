
public class RequestObject {
	private String method;
	private String file;
	
	public RequestObject(String method, String file) {
		this.method = method;
		this.file = file;     
	}

    public RequestObject() {}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}
}

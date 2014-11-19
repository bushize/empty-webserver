
public interface Responder {
	public String getHeader();
	public String getBody();
	public void setHeader();
	public void setBody();
	public void setStatusCode();
	public String getLogs();
}

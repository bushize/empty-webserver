public interface HttpRequest {
	public String method();
	public String getUrl();
	public HttpServer getServer();
	public boolean isAuthorized();
	public String getETag();
}

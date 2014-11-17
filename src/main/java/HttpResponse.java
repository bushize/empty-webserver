import java.io.File;
import java.util.HashMap;


public class HttpResponse {

    private String response;
    private int statusCode;
    private String header = "";
    public HashMap<Integer, String> statusMap = new HashMap<Integer, String>();

    public HttpResponse() {
        statusMap.put(404, "Not Found");
        statusMap.put(401, "Authentication required");
        statusMap.put(200, "OK");
    }

    public void generateResponse(String httpMethod, String url) {
        //System.out.println(url);

        if (httpMethod.equals("GET")) {
            if(url.contains("logs")) {
                statusCode = 401;

            } else if(checkURL(url)) {
                statusCode = 200;
            } else {
                statusCode = 404;
            }
        } else if (httpMethod.equals("POST") || httpMethod.equals("PUT")
                || httpMethod.equals("DELETE")) {
            statusCode = 200;
        } else if(httpMethod.equals("OPTIONS")) {
            statusCode = 200;
            header += "ALLOW: GET,HEAD,POST,OPTIONS,PUT\r\n";
        } else {
            statusCode = 200;
        }

        String statusText = statusMap.get(statusCode);
        String responseBody = "<h1>" + statusCode + " " + statusText + "</h1>";
        String responseHeaderStatus = "HTTP/1.1 " + statusCode + " " + statusText + "\r\n";

        int contentLength = responseBody.length();

        this.response = responseHeaderStatus +
                "Content-Length: " + contentLength + "\r\n" +
                header +
                "Content-Type: text/html\r\n\r\n" +
                responseBody;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    private boolean checkURL(String p) {

        String fileName = p;
        if (!fileName.startsWith("/"))
            fileName = "/" + fileName;

        File f = new File(fileName);

        if (!f.exists())
            return false;

        return true;
    }

    public String getResponse() {
        return this.response;
    }

    public HashMap getStatusMap() {
        return statusMap;
    }

}

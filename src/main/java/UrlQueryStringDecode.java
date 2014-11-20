
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class UrlQueryStringDecode {
    private String urlString;
    private String[] split;
    private String decodedString;
    private Map<String,String> valuePair = new HashMap<String, String>();
    private String mainUrl;
    private String subsetUrl;
    private String formattedQueryString;

    public UrlQueryStringDecode(String urlString){
        this.urlString = urlString;
        parse();
    }

    public String getParamQuery() {
        return this.subsetUrl;
    }

    private void parse() {
        int startIndex = urlString.indexOf("/");
        int endIndex = urlString.indexOf("?");

        this.mainUrl = urlString.substring(startIndex, endIndex);
        this.subsetUrl = urlString.substring(endIndex + 1);
        this.split = subsetUrl.split("&");
        formattedQueryString = "";
        for (String elm : split) {
            String[] pair = elm.split("=");
            if(pair.length > 1) {
                String value = null;
                try {
                    value = decodeQueryString(pair[1]);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                valuePair.put(pair[0], value);
                formattedQueryString += pair[0] + " = " + value + " ";
            }
        }
    }

    public String decodeQueryString(String queryValue) throws UnsupportedEncodingException {
        return decodedString = URLDecoder.decode(queryValue, "UTF-8");
    }

    public String[] getSplit() {
        return split;
    }

    public String getUrlString() {
        return urlString;
    }

    public Map<String, String> getValuePair() {
        return valuePair;
    }

    public String getFormattedQueryStringPairs() {
        return formattedQueryString;
    }
}

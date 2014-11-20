
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class UrlQueryStringDecodeTest {
    @Test
    public void testIfReturnQuery() throws MalformedURLException {
        String url = "http://localhost/?test";

        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = p.getParamQuery();

        assertEquals("test", result);

    }
    /*
        @Test
        public void testIfReturnCorrectValueForParameter() throws Exception {
            String url = "/?test=1";
            UrlQueryStringDecode p = new UrlQueryStringDecode(url);
            String result = p.getParamValue("test");
            Assert.assertEquals("1", result);

            assertEquals("[test=1, secondTest=2]", result);

        }
    */
    @Test
    public void testIfSplitURLByAmpersand() throws Exception {
        String url = "/?test=1&secondTest=2";
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = Arrays.toString(p.getSplit());

        assertEquals("[test=1, secondTest=2]", result);

    }

    @Test
    public void getDecodeURL() throws Exception{
        String url = new String("/parameters?variable_1=Operators%20%3C%2C%20%3E");
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = p.decodeQueryString(url);

        assertEquals("/parameters?variable_1=Operators <, >", result);
    }

    @Test
    public void getQueryStringPairs() throws Exception {
        String url = new String("/parameters?variable_1=Operators%20%3C%2C%20%3E");
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        Map map = p.getValuePair();
        assertEquals("{variable_1=Operators <, >}", map.toString());
    }

    @Test
    public void getFormattedQueryStringOneVariable() throws Exception {
        String url = new String("/parameters?variable_1=Operators%20%3C%2C%20%3E");
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        assertEquals("variable_1 = Operators <, > ", p.getFormattedQueryStringPairs());
    }

    @Test
    public void getFormattedQueryStringTwoVariables() throws Exception {
        String url = new String("/parameters?variable_1=Operators%20%3C%2C%20%3E&v2=abc");
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        assertEquals("variable_1 = Operators <, > v2 = abc ", p.getFormattedQueryStringPairs());
    }
}


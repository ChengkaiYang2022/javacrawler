import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Ignore;
import org.junit.Test;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.config.RequestConfig;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.CookieSpecs;
public class JsoupKafkaTest {
    @Ignore
    public void testJsoup(){
        JsoupKafka jsoupKafka = new JsoupKafka();
        jsoupKafka.run();
    }
//    public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";
//    private static final CloseableHttpClient httpClient = HttpClients
//            .custom()
//            .setUserAgent(USER_AGENT)
//            .setDefaultRequestConfig(
//                    RequestConfig.custom()
//                            .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
//                            .build()).build();
//    public List<URI> getAllRedirectLocations(String link) throws ClientProtocolException, IOException {
//
//
//        List<URI> redirectLocations = null;
//        CloseableHttpResponse response = null;
//        try{
//            HttpClientContext context = HttpClientContext.create();
//            HttpGet httpGet = new HttpGet(link);
//            response = httpClient.execute(httpGet, context);
//
//            // 获取所有的重定向位置
//            redirectLocations = context.getRedirectLocations();
//        } finally{
//            if(response!=null){
//                response.close();
//            }
//        }
//        return redirectLocations;
//    }
//    @Test
//    public void testRedict(String link){
//        try{
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}

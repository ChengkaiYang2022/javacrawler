import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.*;
import us.codecraft.webmagic.selector.PlainText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RedirectDownloader extends HttpClientDownloader {
    private static Logger logger = LoggerFactory.getLogger(RedirectDownloader.class);
    @Override
    public Page download(Request request, Task task) {
        if (logger.isInfoEnabled()) {
            logger.info("downloading page: " + request.getUrl());
        }
        Page page = new Page();
        page.setUrl(new PlainText(getRedirectUrl(request.getUrl())));
        page.setRequest(request);
        page.setStatusCode(200);
        return page;
    }
    public String getRedirectUrl(String url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        String redirectUrl = null;
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            HttpResponse response = httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息
            Object a = httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
            HttpHost targetHost = (HttpHost)httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
            //获取实际的请求对象的URI
            HttpUriRequest realRequest = (HttpUriRequest)httpContext.getAttribute(HttpCoreContext.HTTP_REQUEST);
            redirectUrl = targetHost.toString() + realRequest.getURI().toString();
            logger.info(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return redirectUrl;
    }
}




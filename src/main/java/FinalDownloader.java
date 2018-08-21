import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.*;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.selector.PlainText;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FinalDownloader extends HttpClientDownloader {
    private static Logger logger = LoggerFactory.getLogger(FinalDownloader.class);
    private ProxyProvider proxyProvider;

    private HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
    @Override
    public Page download(Request request, Task task) {
        if (logger.isInfoEnabled()) {
            logger.info("downloading page: " + request.getUrl());
        }
//        HttpClientRequestContext requestContext = this.httpUriRequestConverter.convert(request, task.getSite(), proxy);


        Page page = new Page();
//        page.setRawText(content);
        // TODO seturl为调用的链接
//        page.setUrl(new PlainText(request.getUrl()));
        Proxy proxy = proxyProvider != null ? proxyProvider.getProxy(task) : null;

        HttpClientRequestContext requestContext = httpUriRequestConverter.convert(request, task.getSite(), proxy);
        String re = this.getFinalHTTPLocation(requestContext);
        page.setUrl(new PlainText("http://stockpage.10jqka.com.cn/300179/"));
        page.setRequest(request);
        page.setStatusCode(200);
        return page;
    }
    protected String getFinalHTTPLocation(HttpClientRequestContext requestContext) {
        String last_redirect_url = null;
        try {

            HttpHost target = requestContext.getHttpClientContext().getTargetHost();
            //https://github.com/code4craft/webmagic/issues/626

            //TODO 这里是空的
            List redirectLocations = requestContext.getHttpClientContext().getRedirectLocations();
            //Thread.sleep(5000);

            URI location = URIUtils.resolve(requestContext.getHttpUriRequest().getURI(), target, redirectLocations);
            //logger.debug("Final HTTP location: " + location.toASCIIString());
            last_redirect_url = location.toASCIIString();
            System.out.println(last_redirect_url);
        }catch (URISyntaxException e) {
            logger.error("getFinalHTTPLocation error",e);
        }
        return last_redirect_url;
    }

    //使用httpGet方法 https://blog.csdn.net/u010443481/article/details/44811381
    public void getRedirectInfo(String url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            HttpResponse response = httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
            HttpHost targetHost = (HttpHost)httpContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
            //获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
            HttpUriRequest realRequest = (HttpUriRequest)httpContext.getAttribute(ExecutionContext.HTTP_REQUEST);
            System.out.println("主机地址:" + targetHost);
            System.out.println("URI信息:" + realRequest.getURI());
            HttpEntity entity = response.getEntity();
            if(null != entity){
                System.out.println("响应内容:" + EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset()));
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
    }
}




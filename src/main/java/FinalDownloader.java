import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
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
            Thread.sleep(5000);

            HttpHost target = requestContext.getHttpClientContext().getTargetHost();
            Thread.sleep(5000);
            //https://github.com/code4craft/webmagic/issues/626

            //TODO 这里是空的
            List redirectLocations = requestContext.getHttpClientContext().getRedirectLocations();
            Thread.sleep(5000);

            URI location = URIUtils.resolve(requestContext.getHttpUriRequest().getURI(), target, redirectLocations);
            //logger.debug("Final HTTP location: " + location.toASCIIString());
            last_redirect_url = location.toASCIIString();
        }catch (URISyntaxException e) {
            //logger.error("getFinalHTTPLocation error",e);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return last_redirect_url;
    }

}

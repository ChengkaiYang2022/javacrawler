import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public abstract class FinalDownloader implements Downloader {


    public String download(HttpClientRequestContext request){
        return getFinalHTTPLocation(request);

    }
    protected String getFinalHTTPLocation(HttpClientRequestContext requestContext) {
        String last_redirect_url = null;
        try {
            HttpHost target = requestContext.getHttpClientContext().getTargetHost();
            List redirectLocations = requestContext.getHttpClientContext().getRedirectLocations();
            URI location = URIUtils.resolve(requestContext.getHttpUriRequest().getURI(), target, redirectLocations);
            //logger.debug("Final HTTP location: " + location.toASCIIString());
            last_redirect_url = location.toASCIIString();
        } catch (URISyntaxException e) {
            //logger.error("getFinalHTTPLocation error",e);
        }
        return last_redirect_url;
    }

}

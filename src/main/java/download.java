import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public abstract class download extends HttpClientRequestContext {
    protected String getFinalHTTPLocation() {
        String last_redirect_url = null;
        try {
            HttpHost target = this.getHttpClientContext().getTargetHost();
            List redirectLocations = this.getHttpClientContext().getRedirectLocations();
            URI location = URIUtils.resolve(this.getHttpUriRequest().getURI(), target, redirectLocations);
            //logger.debug("Final HTTP location: " + location.toASCIIString());
            last_redirect_url = location.toASCIIString();
        } catch (URISyntaxException e) {
            //logger.error("getFinalHTTPLocation error",e);
        }
        return last_redirect_url;
    }
}

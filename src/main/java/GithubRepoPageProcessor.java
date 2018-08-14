import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import org.apache.log4j.BasicConfigurator;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println(page.getHeaders());

//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Spider spider = new Spider(new GithubRepoPageProcessor());
        spider.addUrl("http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_");
        spider.thread(5);
        spider.run();
    }
}
import org.apache.log4j.BasicConfigurator;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class GithubRepoPageProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println(page.getUrl());
        System.out.println(page.getHeaders());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Spider spider = new Spider(new GithubRepoPageProcessor());

        while(1==1){
            spider.thread(5);
            spider.run();
            spider.setDownloader(new RedirectDownloader());
            spider.addUrl("http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("xxxxxxx");
        }

    }
}
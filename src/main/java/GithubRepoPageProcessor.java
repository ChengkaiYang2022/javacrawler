import org.apache.log4j.BasicConfigurator;
import scheduler.KafkaConsumerScheduler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class GithubRepoPageProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println(page.getUrl());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Spider spider = new Spider(new GithubRepoPageProcessor());
        spider.setScheduler(new KafkaConsumerScheduler()).thread(5).run();
//        while(1==1){
//            spider.thread(5);
//            spider.run();
//            //spider.setDownloader(new RedirectDownloader());
//            spider.addUrl("http://www.baidu.com/link?url=SCZi8UF0UZ_L688dALi_w1gDL5G4G5QYs30-1mv7nDPcaRH-DKymS_5gRnDhJ4MGUGe5SaKOsc_3I4q_IBOIJ_");
//            String URL3 = "http://www.baidu.com/link?url=---yJOodqyT2CM0ueRB8pF3fWwYEe8Bb0GQp8rctUtho_Q7N5VnGO9bK8I_4D5rb021bASfA7sIvl6WqjisCaK";
//            spider.addUrl(URL3);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
import org.junit.Ignore;
import org.junit.Test;
import scheduler.KafkaConsumerScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

public class KafkaConsumerSchedulerTest {
    //TODO KafkaConsumerScheduler还未实现
    @Ignore
    public void test_kafkaConsumerScheduler_init(){
        KafkaConsumerScheduler kafkaConsumerScheduler = new KafkaConsumerScheduler();

    }
    @Ignore
    public void test_spider(){
        Spider.create(new GithubRepoPageProcessor()).setScheduler(new KafkaConsumerScheduler()).run();
    }
}


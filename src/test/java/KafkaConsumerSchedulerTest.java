import org.junit.Test;
import scheduler.KafkaConsumerScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

public class KafkaConsumerSchedulerTest {
    @Test
    public void test_kafkaConsumerScheduler_init(){
        KafkaConsumerScheduler kafkaConsumerScheduler = new KafkaConsumerScheduler();

    }
    @Test
    public void test_spider(){
        Spider.create(new GithubRepoPageProcessor()).setScheduler(new KafkaConsumerScheduler()).run();
    }
}


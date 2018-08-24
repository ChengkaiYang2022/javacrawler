import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.BasicConfigurator;
import pipeline.KafkaPublisherPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.Properties;

public class MyProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        System.out.println(page.getUrl());
        page.putField("Redictored_Link",page.getUrl());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Spider spider = new Spider(new GithubRepoPageProcessor());

        spider.setDownloader(new RedirectDownloader());
        spider.addPipeline(new KafkaPublisherPipeline());
        //spider.addPipeline(new FilePipeline("/home/yangck/githubWorkspace/javacrawler/result/"));
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "blackbox01.jry.com:6667");
        properties.put("group.id", "CRAWLER_JAVA");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("link_test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);

                spider.addUrl(record.value());
                spider.thread(5);
                spider.run();
            }
        }

    }

}
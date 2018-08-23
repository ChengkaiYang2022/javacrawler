package scheduler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.MonitorableScheduler;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class KafkaConsumerScheduler extends DuplicateRemovedScheduler implements MonitorableScheduler {

    public static final int KAFKA_QUEUE_SIZE = 3;
    private BlockingQueue<Request> kafkaQueue = new LinkedBlockingDeque<Request>();

    public KafkaConsumerScheduler()  {
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

            if(kafkaQueue.size() < KAFKA_QUEUE_SIZE){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record);
                    Request request = new Request();
                    request.setUrl("http://futures.jrj.com.cn/gzqh/2016/06/20213521091327.shtml");
                    kafkaQueue.add(request);
                }
            }else{
                try{
                    Thread.sleep(200);
                    System.out.println("wait" + kafkaQueue.size());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }


    @Override
    protected void pushWhenNoDuplicate(Request request, Task task) {
        kafkaQueue.add(request);
    }

    @Override
    public synchronized Request poll(Task task) {
        return kafkaQueue.poll();
    }


    @Override
    public int getLeftRequestsCount(Task task) {
        return kafkaQueue.size();
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return getDuplicateRemover().getTotalRequestsCount(task);
    }
}

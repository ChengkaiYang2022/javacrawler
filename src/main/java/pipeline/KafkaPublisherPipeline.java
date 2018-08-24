package pipeline;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Properties;

public class KafkaPublisherPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());
    Properties props = new Properties();
    {
        props.put("bootstrap.servers", "blackbox01.jry.com:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }
    Producer<String, String> producer = new KafkaProducer<String, String>(props);
    @Override
    public void process(ResultItems resultItems, Task task){

        resultItems.getAll();
        resultItems.getRequest().getUrl();
        resultItems.getAll().get("redirectUrl");
        String result = resultItems.getRequest().getUrl()+","+resultItems.getAll().get("redirectUrl");
        producer.send(new ProducerRecord<String, String>("link_test_2",result));
        logger.info("send to kafka success.");
    }
}

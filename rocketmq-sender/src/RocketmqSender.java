///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 8
//DEPS org.apache.rocketmq:rocketmq-client:4.9.3

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketmqSender {
    public static void main(String[] args) throws Exception {
        String topic = args[0];
        String body = args[1];
        DefaultMQProducer producer = new DefaultMQProducer("sender-app");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        SendResult sendResult = producer.send(new Message(topic, body.getBytes(RemotingHelper.DEFAULT_CHARSET)));
        System.out.printf("%s%n", sendResult);
        producer.shutdown();
        System.exit(0);
    }
}

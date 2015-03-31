package com.doctor.example.quickstart;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 
 * @see 官方示例
 * @author doctor
 *
 * @time 2015年3月31日 下午3:49:56
 */
public class Producer {

	public static void main(String[] args) throws MQClientException {
		DefaultMQProducer producer = new DefaultMQProducer("producerGroup-1");
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.setInstanceName("producer-a");
		producer.start();

		try {
			for (int i = 0; i < 100; i++) {
				TimeUnit.SECONDS.sleep(2);
				Message message = new Message("topic-test", "tags-a", (LocalDateTime.now().toString() + i).getBytes(StandardCharsets.UTF_8));
				SendResult sendResult = producer.send(message);
				System.out.println(sendResult);

			}
		} catch (RemotingException | MQBrokerException | InterruptedException e) {
			e.printStackTrace();
		}

		producer.shutdown();
	}

}

package com.doctor.example.quickstart;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 
 * @see 官方示例
 * @author doctor
 *
 * @time 2015年3月31日 下午3:50:14
 */
public class Consumer {

	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("consumerGroup-1");
		pushConsumer.setNamesrvAddr("127.0.0.1:9876");
		pushConsumer.setInstanceName("pushConsumer-a");
		pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		pushConsumer.subscribe("topic-test", "*");

		pushConsumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName() + "Receive New Messages:" + msgs);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		pushConsumer.start();
		System.err.println("Consumer Started.");
	}

}

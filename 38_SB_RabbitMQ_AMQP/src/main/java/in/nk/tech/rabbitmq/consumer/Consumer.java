package in.nk.tech.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.nk.tech.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consumeMsg(String message) {
		log.info("Message Consumed, {}",message);
	}
	
	@RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
	public void consumeUserData(User user) {
		log.info("Data Consumed, {}",user);
	}
	
}

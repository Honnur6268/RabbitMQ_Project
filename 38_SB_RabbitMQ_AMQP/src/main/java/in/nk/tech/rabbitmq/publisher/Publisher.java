package in.nk.tech.rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.nk.tech.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Publisher {

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing-key.name}")
	private String routingKey;

	@Value("${rabbitmq.routing-key.json.key}")
	private String jsonRoutingKey;

//	private static final String DIRECT_EXCHANGE =  "nk_direct_exchange";

	private RabbitTemplate rabbitTemplate;

	public Publisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public String send(String message) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
		log.info("Message Sent {}", message);
		return "Message Sent -" + message;
	}

	public String sendUserDetails(User user) {
		rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, user);
		log.info("Message Sent {}", user);
		return "Message Sent - " + user;
	}

}

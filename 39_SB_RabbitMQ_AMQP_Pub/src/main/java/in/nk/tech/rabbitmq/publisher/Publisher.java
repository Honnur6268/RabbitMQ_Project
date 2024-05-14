package in.nk.tech.rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import in.nk.tech.rabbitmq.constants.AppConstants;
import in.nk.tech.rabbitmq.dto.OrderStatus;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Publisher {

	private RabbitTemplate rabbitTemplate;

	public Publisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public String sendUserDetails(OrderStatus orderStatus) {
		rabbitTemplate.convertAndSend(AppConstants.EXCHANGE, AppConstants.ROUTING_KEY, orderStatus);
		log.info("Message Sent {}", orderStatus);
		return "Message Sent - " + orderStatus;
	}

}

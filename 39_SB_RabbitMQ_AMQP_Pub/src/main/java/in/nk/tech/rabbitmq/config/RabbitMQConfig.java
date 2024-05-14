package in.nk.tech.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.nk.tech.rabbitmq.constants.AppConstants;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queue() {
		return new Queue(AppConstants.QUEUE);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(AppConstants.EXCHANGE);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(directExchange()).with(AppConstants.ROUTING_KEY);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}

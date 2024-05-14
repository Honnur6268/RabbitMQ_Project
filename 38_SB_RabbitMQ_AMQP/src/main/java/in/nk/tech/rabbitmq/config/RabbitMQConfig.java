package in.nk.tech.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueueName;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	private static final String DIRECT_EXCHANGE = "nk_direct_exchange";

	@Value("${rabbitmq.routing-key.name}")
	private String routingKey;

	@Value("${rabbitmq.routing-key.json.key}")
	private String jsonRoutingKey;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueueName);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(DIRECT_EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
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

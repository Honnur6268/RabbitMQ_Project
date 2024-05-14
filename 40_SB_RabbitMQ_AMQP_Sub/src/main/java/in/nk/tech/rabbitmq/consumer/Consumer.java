package in.nk.tech.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nk.tech.rabbitmq.constants.AppConstants;
import in.nk.tech.rabbitmq.dto.OrderStatus;
import in.nk.tech.rabbitmq.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@Autowired
	private EmailUtils emailUtils;

	@RabbitListener(queues = AppConstants.QUEUE)
	public void consumeUserData(OrderStatus orderStatus) {
		String to = orderStatus.getOrder().getUser().getEmail();
		System.out.println("to: " + to);
		String name = orderStatus.getOrder().getUser().getName();
		String subject = null;
		if ("Processing".equalsIgnoreCase(orderStatus.getStatus())) {
			subject = "Order Placed";
			String body = "<h3>Hi " + name + ", </h3> " + "<p>Your order placed successfully.</p>" + "<p>Order Id: "
					+ "<b>" + orderStatus.getOrder().getOrderId() + "</b></p>" + "<p>Order Name: " + "<b>"
					+ orderStatus.getOrder().getProduct() + "</b></p>" + "<p>Quantity: " + "<b>"
					+ orderStatus.getOrder().getQuantitiy() + "</b></p>" + "<p>Price: " + "<b>"
					+ orderStatus.getOrder().getPrice() + "</b></p>";

			emailUtils.sendEmail(to, subject, body);
			log.info("Data Consumed, {}", orderStatus);
		} else {
			subject = "Order Delivered";
			String body = "<h3>Hi " + name + ", </h3> " + "<p>Your order delivered successfully.</p>" + "<p>Order Id: "
					+ "<b>" + orderStatus.getOrder().getOrderId() + "</b></p>" + "<p>Order Name: " + "<b>"
					+ orderStatus.getOrder().getProduct() + "</b></p>" + "<p>Quantity: " + "<b>"
					+ orderStatus.getOrder().getQuantitiy() + "</b></p>" + "<p>Price: " + "<b>"
					+ orderStatus.getOrder().getPrice() + "</b></p>" + "<p>Thank You</p>";

			emailUtils.sendEmail(to, subject, body);
			log.info("Data Consumed, {}", orderStatus);
		}
	}

}

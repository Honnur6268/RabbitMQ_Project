package in.nk.tech.rabbitmq.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nk.tech.rabbitmq.dto.OrderStatus;
import in.nk.tech.rabbitmq.publisher.Publisher;

@RestController
@RequestMapping("/api/v1")
public class MessageRestController {

	@Autowired
	private Publisher publisher;

	@PostMapping("/send-data")
	public ResponseEntity<String> sendUserData(@RequestBody OrderStatus orderStatus) {
		String msg = publisher.sendUserDetails(orderStatus);
		return ResponseEntity.ok(msg);
	}
}

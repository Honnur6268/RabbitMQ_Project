package in.nk.tech.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

	private Integer orderStatusId;
	private String status;
	private String message;
	private Order order;
}

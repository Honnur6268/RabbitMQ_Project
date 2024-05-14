package in.nk.tech.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	private Integer orderId;
	private String product;
	private Integer quantitiy;
	private Double price;
	private User user;
}

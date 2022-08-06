package com.ecommerce.service;

import com.ecommerce.dispatcher.KafkaDispatcher;
import com.ecommerce.model.Order;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain
{
	public void main()throws ExecutionException, InterruptedException, IOException
	{
		this.orderDispatcher();
		this.emailDispatcher();
	}

	void orderDispatcher() throws IOException, ExecutionException, InterruptedException
	{
		try (var dispatcher = new KafkaDispatcher<Order>())
		{
			for (var i = 0; i < 10; i++)
			{
				var userId = UUID.randomUUID().toString();
				var orderId = UUID.randomUUID().toString();
				var value = Math.random() * 5000 + 1;
				var order = new Order(userId, orderId, new BigDecimal(value));
				dispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
			}
		}
	}

	void emailDispatcher() throws IOException, ExecutionException, InterruptedException
	{
		try (var dispatcher = new KafkaDispatcher<String>())
		{
			for (var i = 0; i < 10; i++)
			{
				var userId = UUID.randomUUID().toString();
				var email =  "Thank you for your order! We are processing your order!";

				dispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
			}
		}
	}
}

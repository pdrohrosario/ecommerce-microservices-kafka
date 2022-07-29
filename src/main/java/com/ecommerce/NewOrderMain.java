package com.ecommerce;

import com.ecommerce.dispatcher.KafkaDispatcher;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain
{
	public static void main(String[] args) throws ExecutionException, InterruptedException, IOException
	{
		try(var dispatcher = new KafkaDispatcher())
		{
			for (var i = 0; i < 10; i++)
			{
				var key = UUID.randomUUID().toString();

				var value = "132123,67523,1234";
				dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

				var emailText = "Great ! We are processing your order!";
				dispatcher.send("ECOMMERCE_SEND_EMAIL", key, emailText);
			}
		}
	}
}

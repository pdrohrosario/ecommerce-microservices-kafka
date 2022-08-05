package com.ecommerce.service;

import java.util.HashMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService
{

	public static void main(String[] args)
	{
		var emailService = new EmailService();
		var service = new KafkaService(EmailService.class.getSimpleName(),"ECOMMERCE_SEND_EMAIL", emailService::parse,String.class, new HashMap<>());

		service.run();
	}

	private void parse(ConsumerRecord<String, String> record)
	{
		System.out.println("------------------------------------------");
		System.out.println("Send email for you");
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.partition());
		System.out.println(record.offset());
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			// ignoring
			e.printStackTrace();
		}
		System.out.println("Order processed");
	}
}

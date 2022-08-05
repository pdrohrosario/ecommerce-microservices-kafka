package com.ecommerce;

import com.sun.jdi.ObjectReference;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main
{
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
	{
		NewOrderMain newOrderMain = new NewOrderMain();
		newOrderMain.main();
	}
}

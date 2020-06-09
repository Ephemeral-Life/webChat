package Ephemeral.Spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class getAC
{
	public static ClassPathXmlApplicationContext ac()
	{
		return new ClassPathXmlApplicationContext("spring.xml");
	}
}

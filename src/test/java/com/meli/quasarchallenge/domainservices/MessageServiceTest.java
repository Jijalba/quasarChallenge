package com.meli.quasarchallenge.domainservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.IMessageService;

@SpringBootTest
public class MessageServiceTest {

	@Autowired
	private IMessageService messageService;

	@Test
	public void challengeExampleTest() throws DomainException {

		var messageList = new ArrayList<List<String>>();

		messageList.add(Arrays.asList("Este","","","mensaje",""));
		messageList.add(Arrays.asList("","es","","","secreto"));
		messageList.add(Arrays.asList("Este","","un","",""));

		String message = messageService.getMessage(messageList);

		assertEquals(message,"Este es un mensaje secreto");

	}

	@Test
	public void nNoWordsTest() throws DomainException {

		var messageList = new ArrayList<List<String>>();

		messageList.add(Arrays.asList("Este","es","","mensaje"));
		messageList.add(Arrays.asList("","","un","mensaje"));
		messageList.add(Arrays.asList("","","",""));

		String message = messageService.getMessage(messageList);

		assertEquals(message,"Este es un mensaje");

	}

	@Test
	public void allEqualWords() throws DomainException {

		var messageList = new ArrayList<List<String>>();

		messageList.add(Arrays.asList("Este","","",""));
		messageList.add(Arrays.asList("","","este","este"));
		messageList.add(Arrays.asList("","este","",""));

		String message = messageService.getMessage(messageList);

		assertEquals(message,"Este este este este");

	}

	@Test
	public void allNoWords() throws DomainException {

		var messageList = new ArrayList<List<String>>();

		messageList.add(new ArrayList<String>());
		messageList.add(new ArrayList<String>());
		messageList.add(new ArrayList<String>());

		try {
			messageService.getMessage(messageList);
		
		}
		catch(Exception ex) {
			assertEquals(ex.getMessage(),"No se recibieron mensajes");
			
		}

	}
	
	@Test
	public void differentSizesMessages() throws DomainException {

		var messageList = new ArrayList<List<String>>();

		messageList.add(Arrays.asList("","Este","","","mensaje",""));
		messageList.add(Arrays.asList("","","es","","","secreto"));
		messageList.add(Arrays.asList("","","","","","","","Este","","un","",""));

		String message = messageService.getMessage(messageList);
		
		assertEquals(message,"Este es un mensaje secreto");

	}

}

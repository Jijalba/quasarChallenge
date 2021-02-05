package com.meli.quasarchallenge.domainservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.quasarchallenge.datarepositories.InterceptionsRepository;
import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.IMessageService;

@Service
public class MessageService implements IMessageService{

	@Autowired
	InterceptionsRepository interceptions;
	
	public String getMessage(List<List<String>> messageList) throws DomainException {

		if(messageList.stream().allMatch(m -> m.isEmpty()))
			throw new DomainException("No se recibieron mensajes");

		var phraseList = new ArrayList<String>();

		var wordAmmount = getWordAmmount(messageList);

		for(int i = 0; i< wordAmmount; i++)				
			for(List<String> message: messageList)
			{				
				var word = message.get(message.size() - wordAmmount + i);

				if(!word.isBlank()) { 
					phraseList.add(message.get(message.size() - wordAmmount + i));
					break; // No es la mejor práctica, pero evita ciclos innecesarios 	
				}

			}

		return String.join(" ",phraseList);

	}
	
	int getWordAmmount(List<List<String>> messageList) {

		var firstWord = "";
		var ndx = 0;
		var j = 0;

		firstWord = messageList.get(ndx).get(j);

		while(firstWord.isBlank()) {

			if(++ndx >= messageList.size())
			{
				j++;
				ndx = 0 ;
			}

			firstWord = messageList.get(ndx).get(j);

		}

		return messageList.get(ndx).size() - j;

	}

}

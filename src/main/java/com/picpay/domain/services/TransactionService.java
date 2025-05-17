package com.picpay.domain.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.picpay.domain.dtos.AuthorizationResponse;
import com.picpay.domain.dtos.TransactionDTO;
import com.picpay.domain.transaction.Transaction;
import com.picpay.domain.user.User;
import com.picpay.repositories.TransactionRepository;

public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;


	public void createTransaction(TransactionDTO transaction) throws Exception {
		User sender = userService.findUserById(transaction.getSenderId());
		User receiver = userService.findUserById(transaction.getReceiverId());
		
		userService.validateTransaction(sender, transaction.getValue());
		
		boolean isAuthorized = authorizeTransaction(sender, transaction.getValue());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.getValue());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.getValue()));
		receiver.setBalance(receiver.getBalance().add(transaction.getValue()));

		repository.save(newTransaction);
		userService.saveUser(sender);
		userService.saveUser(receiver);

	}

	public boolean authorizeTransaction(User sender, BigDecimal value) {
		ResponseEntity<AuthorizationResponse> authorizationResponse = restTemplate
				.getForEntity("https://util.devi.tools/api/v2/authorize", AuthorizationResponse.class);

		if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
			return "Autorizado".equalsIgnoreCase(authorizationResponse.getBody().getMessage());
		} else
			return false;
	}

}

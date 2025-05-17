package com.picpay.domain.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.domain.user.User;
import com.picpay.domain.user.UserType;
import com.picpay.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT){
			throw new Exception("Usuario do tipo lojista não esta autorizado a realizar transação");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo Insuficiente");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("Usuario não encotrado"));
	}
	
	public void saveUser(User user) {
		repository.save(user);
	}

}

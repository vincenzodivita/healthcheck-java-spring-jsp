package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.UserConverter;
import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;

@Service
public class UserService extends AbstractService<User, UserDTO> {

	@Autowired
	private UserConverter converter;
	@Autowired
	private UserRepository repository;

	public UserDTO findByEmailAndPassword(String email, String password) {
		return converter.toDTO(repository.findByEmailAndPassword(email, password));
	}

	public UserDTO findByEmail(String email){
		return converter.toDTO(repository.findByEmail(email));
	}


}

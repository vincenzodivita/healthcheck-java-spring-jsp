package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String Email);

}

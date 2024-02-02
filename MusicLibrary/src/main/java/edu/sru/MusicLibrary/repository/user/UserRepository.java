package edu.sru.MusicLibrary.repository.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

	List<User> findByUsernameStartingWith(String query);

	List<User> findByEmailContaining(String query);

}

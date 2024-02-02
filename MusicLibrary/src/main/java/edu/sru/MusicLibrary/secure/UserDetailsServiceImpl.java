package edu.sru.MusicLibrary.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.sru.MusicLibrary.repository.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		edu.sru.MusicLibrary.domain.user.User user1 = userRepository.findByUsername(username);
		if (user1 == null || !user1.getEnabled()) {
			throw new UsernameNotFoundException(username);
		}

		UserDetails user = User.withUsername(user1.getUsername()).password(user1.getPassword())
				.authorities(user1.getRole()).build();
		return user;
	}

}
package edu.sru.MusicLibrary.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.MusicLibrary.controller.EmailController;
import edu.sru.MusicLibrary.controller.UtilityController;
import edu.sru.MusicLibrary.domain.user.User;
import edu.sru.MusicLibrary.repository.user.UserRepository;

@Service
public class TwoFactorAuthentication {

	@Autowired
	private EmailController emailController;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UtilityController util;

	public void send2FACode(User user) {
		String code = util.randomStringGenerator();
		user.setTwoFactorAuthenticationCode(code);
		emailController.sendTwoFactorAuthEmail(user, code);
		userRepository.save(user);
	}

	public boolean verify2FACode(User user, String code) {
		if (user.getTwoFactorAuthenticationCode().equals(code)) {
			user.setTwoFactorAuthenticationCode(null);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public void set2FAEnabled(User user, boolean enabled) {
		user.setTwoFactorEnabled(enabled);
		userRepository.save(user);
	}
}

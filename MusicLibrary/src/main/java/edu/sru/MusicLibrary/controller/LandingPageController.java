package edu.sru.MusicLibrary.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sru.MusicLibrary.domain.user.User;
import edu.sru.MusicLibrary.repository.user.UserRepository;
import edu.sru.MusicLibrary.secure.TwoFactorAuthentication;
import edu.sru.MusicLibrary.service.UserService;

@Controller
public class LandingPageController {

	@Autowired
	private UserService userService;

	@Autowired
	private TwoFactorAuthentication twoFactorAuthentication;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping({ "homePage" })
	public String showHomePage(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("user", user);
		return "homePage";
	}

	@RequestMapping({ "/updateUser" })
	public String showHomePage(Model model, @RequestParam(value = "2FA", required = false) String is2FAEnabled,
			Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("user", user);

		boolean twoFactorEnabled = "on".equals(is2FAEnabled);
		if (twoFactorEnabled) {
			twoFactorAuthentication.set2FAEnabled(user, true);
		} else {
			twoFactorAuthentication.set2FAEnabled(user, false);
		}

		model.addAttribute("user", user);
		userRepository.save(user);

		return "homePage";
	}

}

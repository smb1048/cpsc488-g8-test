package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/account")
	public String account() {
		return "account";
	}

	@GetMapping("/playlists")
	public String playlists() {
		return "playlists";
	}

	@GetMapping("/explore")
	public String explore() {
		return "explore";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}

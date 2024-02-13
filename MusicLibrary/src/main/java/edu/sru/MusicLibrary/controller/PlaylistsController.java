package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.repository.PlaylistRepository;

@Controller
public class PlaylistsController {

	private PlaylistRepository playlistRepository;

	public PlaylistsController(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}

    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/playlists"})
    public String showPlaylistList(Model model) {
        model.addAttribute("playlists", playlistRepository.findAll());
        return "playlists";
    }
}
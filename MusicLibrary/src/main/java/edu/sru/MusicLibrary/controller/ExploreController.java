package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.repository.AlbumRepository;
import edu.sru.MusicLibrary.repository.MusicRepository;
@Controller
public class ExploreController {
	public ExploreController() {
        // Default constructor
    	}

	private AlbumRepository albumRepository;
	private MusicRepository musicRepository;

	public ExploreController(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
	public ExploreController(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}

	@RequestMapping({"/explore"})
    public String showAlbumList(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("music", musicRepository.findAll());
        return "explore";
    }
}

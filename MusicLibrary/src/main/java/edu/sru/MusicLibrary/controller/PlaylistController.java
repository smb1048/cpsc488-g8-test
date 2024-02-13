package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Playlist;
import edu.sru.MusicLibrary.repository.PlaylistRepository;

@Controller
public class PlaylistController {
	
	private PlaylistRepository playlistRepository;

	public PlaylistController(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showPlaylistList(Model model) {
        model.addAttribute("playlists", playlistRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-playlist HTML, to add a playlist
	@RequestMapping({"/signup"})
    public String showSignUpForm(Playlist playlist) {
        return "add-playlist";
    }
    
	//Mapping for the /signup URL - to add a playlist
    @RequestMapping({"/addplaylist"})
    public String addPlaylist(@Validated Playlist playlist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-playlist";
        }
        
        playlistRepository.save(playlist);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/playlist URL to edit a playlist 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Playlist playlist = playlistRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid playlist Id:" + id));
        
        model.addAttribute("playlist", playlist);
        return "update-playlist";
    }
    
    //Mapping for the /update/id URL to update a playlist 
    @PostMapping("/update/{id}")
    public String updatePlaylist(@PathVariable("id") long id, @Validated Playlist playlist, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            playlist.setId(id);
            return "update-playlist";
        }
            
        playlistRepository.save(playlist);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a playlist     
    @GetMapping("/delete/{id}")
    public String deletePlaylist(@PathVariable("id") long id, Model model) {
        Playlist playlist = playlistRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid playlist Id:" + id));
        playlistRepository.delete(playlist);
        return "redirect:/index";
    }
}
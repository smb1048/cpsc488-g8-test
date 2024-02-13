package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Album;
import edu.sru.MusicLibrary.repository.AlbumRepository;

@Controller
public class AlbumController {
	
	private AlbumRepository albumRepository;

	public AlbumController(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showAlbumList(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-album HTML, to add a album
	@RequestMapping({"/signup"})
    public String showSignUpForm(Album album) {
        return "add-album";
    }
    
	//Mapping for the /signup URL - to add a album
    @RequestMapping({"/addalbum"})
    public String addAlbum(@Validated Album album, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-album";
        }
        
        albumRepository.save(album);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/album URL to edit a album 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Album album = albumRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid album Id:" + id));
        
        model.addAttribute("album", album);
        return "update-album";
    }
    
    //Mapping for the /update/id URL to update a album 
    @PostMapping("/update/{id}")
    public String updateAlbum(@PathVariable("id") long id, @Validated Album album, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            album.setId(id);
            return "update-album";
        }
            
        albumRepository.save(album);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a album     
    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable("id") long id, Model model) {
        Album album = albumRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid album Id:" + id));
        albumRepository.delete(album);
        return "redirect:/index";
    }
}

package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Artist;
import edu.sru.MusicLibrary.repository.ArtistRepository;

@Controller
public class ArtistController {
	
	private ArtistRepository artistRepository;

	public ArtistController(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showArtistList(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-artist HTML, to add a artist
	@RequestMapping({"/signup"})
    public String showSignUpForm(Artist artist) {
        return "add-artist";
    }
    
	//Mapping for the /signup URL - to add a artist
    @RequestMapping({"/addartist"})
    public String addArtist(@Validated Artist artist, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-artist";
        }
        
        artistRepository.save(artist);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/artist URL to edit a artist 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Artist artist = artistRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        
        model.addAttribute("artist", artist);
        return "update-artist";
    }
    
    //Mapping for the /update/id URL to update a artist 
    @PostMapping("/update/{id}")
    public String updateArtist(@PathVariable("id") long id, @Validated Artist artist, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            artist.setId(id);
            return "update-artist";
        }
            
        artistRepository.save(artist);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a artist     
    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable("id") long id, Model model) {
        Artist artist = artistRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid artist Id:" + id));
        artistRepository.delete(artist);
        return "redirect:/index";
    }
}
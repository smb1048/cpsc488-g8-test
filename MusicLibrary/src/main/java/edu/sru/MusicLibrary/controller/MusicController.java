package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Music;
import edu.sru.MusicLibrary.repository.MusicRepository;

@Controller
public class MusicController {
	
	private MusicRepository musicRepository;

	public MusicController(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showMusicList(Model model) {
        model.addAttribute("musics", musicRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-music HTML, to add a music
	@RequestMapping({"/signup"})
    public String showSignUpForm(Music music) {
        return "add-music";
    }
    
	//Mapping for the /signup URL - to add a music
    @RequestMapping({"/addmusic"})
    public String addMusic(@Validated Music music, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-music";
        }
        
        musicRepository.save(music);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/music URL to edit a music 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Music music = musicRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));
        
        model.addAttribute("music", music);
        return "update-music";
    }
    
    //Mapping for the /update/id URL to update a music 
    @PostMapping("/update/{id}")
    public String updateMusic(@PathVariable("id") long id, @Validated Music music, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            music.setId(id);
            return "update-music";
        }
            
        musicRepository.save(music);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a music     
    @GetMapping("/delete/{id}")
    public String deleteMusic(@PathVariable("id") long id, Model model) {
        Music music = musicRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));
        musicRepository.delete(music);
        return "redirect:/index";
    }
}
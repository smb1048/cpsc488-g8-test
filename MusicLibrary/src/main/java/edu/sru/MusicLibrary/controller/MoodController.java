package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Mood;
import edu.sru.MusicLibrary.repository.MoodRepository;

@Controller
public class MoodController {
	
	private MoodRepository moodRepository;

	public MoodController(MoodRepository moodRepository) {
		this.moodRepository = moodRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showMoodList(Model model) {
        model.addAttribute("moods", moodRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-mood HTML, to add a mood
	@RequestMapping({"/signup"})
    public String showSignUpForm(Mood mood) {
        return "add-mood";
    }
    
	//Mapping for the /signup URL - to add a mood
    @RequestMapping({"/addmood"})
    public String addMood(@Validated Mood mood, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-mood";
        }
        
        moodRepository.save(mood);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/mood URL to edit a mood 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Mood mood = moodRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid mood Id:" + id));
        
        model.addAttribute("mood", mood);
        return "update-mood";
    }
    
    //Mapping for the /update/id URL to update a mood 
    @PostMapping("/update/{id}")
    public String updateMood(@PathVariable("id") long id, @Validated Mood mood, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            mood.setId(id);
            return "update-mood";
        }
            
        moodRepository.save(mood);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a mood     
    @GetMapping("/delete/{id}")
    public String deleteMood(@PathVariable("id") long id, Model model) {
        Mood mood = moodRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid mood Id:" + id));
        moodRepository.delete(mood);
        return "redirect:/index";
    }
}
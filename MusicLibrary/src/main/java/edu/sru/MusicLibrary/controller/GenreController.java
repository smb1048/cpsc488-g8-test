package edu.sru.MusicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.MusicLibrary.domain.Genre;
import edu.sru.MusicLibrary.repository.GenreRepository;

@Controller
public class GenreController {
	
	private GenreRepository genreRepository;

	public GenreController(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
    
    //Mapping for the /index URL when initiated through Tomcat
    @RequestMapping({"/index"})
    public String showGenreList(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "index";
    }

    //Mapping for the /signup URL - calls the add-genre HTML, to add a genre
	@RequestMapping({"/signup"})
    public String showSignUpForm(Genre genre) {
        return "add-genre";
    }
    
	//Mapping for the /signup URL - to add a genre
    @RequestMapping({"/addgenre"})
    public String addGenre(@Validated Genre genre, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-genre";
        }
        
        genreRepository.save(genre);
        return "redirect:/index";
    }
    
  
    //Mapping for the /edit/genre URL to edit a genre 
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Genre genre = genreRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid genre Id:" + id));
        
        model.addAttribute("genre", genre);
        return "update-genre";
    }
    
    //Mapping for the /update/id URL to update a genre 
    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable("id") long id, @Validated Genre genre, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            genre.setId(id);
            return "update-genre";
        }
            
        genreRepository.save(genre);
        return "redirect:/index";
    }
    
    //Mapping for the /delete/id URL to delete a genre     
    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") long id, Model model) {
        Genre genre = genreRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid genre Id:" + id));
        genreRepository.delete(genre);
        return "redirect:/index";
    }
}
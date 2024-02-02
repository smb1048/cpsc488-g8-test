package edu.sru.MusicLibrary.domains;

import java.util.Set;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	private Set<Music> allMusic;

	@ManyToOne
	private Artist artists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

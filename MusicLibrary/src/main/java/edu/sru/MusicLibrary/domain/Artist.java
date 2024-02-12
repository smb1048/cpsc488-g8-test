package edu.sru.MusicLibrary.domain;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	private Set<Music> allMusic;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	private Set<Album> albums;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

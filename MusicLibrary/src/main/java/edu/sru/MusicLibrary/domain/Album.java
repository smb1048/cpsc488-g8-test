package edu.sru.MusicLibrary.domain;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private byte[] image;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	private Set<Music> allMusic;

	@ManyToOne
	private Artist artist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}

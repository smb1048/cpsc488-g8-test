package edu.sru.MusicLibrary.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer rating;
	private String songName;
	private String dateAdded;
	private byte[] image;

	@ManyToMany(mappedBy = "musicName")
	private Set<Mood> moods = new HashSet<>();

	@ManyToMany(mappedBy = "musicName")
	private Set<Genre> genres = new HashSet<>();

	@ManyToMany(mappedBy = "musicName")
	private Set<Playlist> playlists = new HashSet<>();

	@ManyToOne
	private Artist artist;

	@ManyToOne
	private Album album;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		rating = rating;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

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

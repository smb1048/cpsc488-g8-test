package edu.sru.MusicLibrary.domains;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer Rating;
	private String songName;
	private String dateAdded;

	@ManyToMany(mappedBy = "moods")
	private Set<Mood> moods = new HashSet<>();

	@ManyToMany(mappedBy = "genres")
	private Set<Genre> genres = new HashSet<>();

	@ManyToMany(mappedBy = "playlists")
	private Set<Playlist> playlists = new HashSet<>();

	@ManyToOne
	private Artist artists;

	@ManyToOne
	private Artist albums;

	public Integer getRating() {
		return Rating;
	}

	public void setRating(Integer rating) {
		Rating = rating;
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

}

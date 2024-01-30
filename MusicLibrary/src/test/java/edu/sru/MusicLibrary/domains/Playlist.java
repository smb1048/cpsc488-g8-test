package edu.sru.ajr1028.domains;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class Playlist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String playlistName;
	private Integer playlistRating;
	private String dateAdded;
	
	@ManyToMany
	@JoinTable(name="playlist_category",
		joinColumns=@JoinColumn(name="playlist_id"),
		inverseJoinColumns=@JoinColumn(name="music_id"))
	private Set<Music> musicName = new HashSet<>();

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public Integer getPlaylistRating() {
		return playlistRating;
	}

	public void setPlaylistRating(Integer playlistRating) {
		this.playlistRating = playlistRating;
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

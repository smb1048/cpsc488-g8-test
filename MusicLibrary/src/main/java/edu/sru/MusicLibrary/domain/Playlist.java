package edu.sru.MusicLibrary.domain;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
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

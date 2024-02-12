package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Playlist;

public interface PlaylistRepository extends CrudRepository <Playlist, Long>{

}

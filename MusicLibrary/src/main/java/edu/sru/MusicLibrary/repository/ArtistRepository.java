package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Artist;

public interface ArtistRepository extends CrudRepository <Artist, Long>{

}

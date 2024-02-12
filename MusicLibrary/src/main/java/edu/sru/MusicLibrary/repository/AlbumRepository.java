package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Album;

public interface AlbumRepository extends CrudRepository <Album, Long>{

}

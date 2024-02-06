package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Music;

public interface MusicRepository extends CrudRepository <Music, Long>{

}

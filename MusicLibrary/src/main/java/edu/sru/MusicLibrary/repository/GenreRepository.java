package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}

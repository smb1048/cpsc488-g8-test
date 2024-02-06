package edu.sru.MusicLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.Mood;

public interface MoodRepository extends CrudRepository <Mood, Long>{

}

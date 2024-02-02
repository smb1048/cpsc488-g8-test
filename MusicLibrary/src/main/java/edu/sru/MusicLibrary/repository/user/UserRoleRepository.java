package edu.sru.MusicLibrary.repository.user;

import org.springframework.data.repository.CrudRepository;

import edu.sru.MusicLibrary.domain.user.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
package edu.sru.MusicLibrary.secure;

public class UnauthenticatedUserException extends RuntimeException {
	public UnauthenticatedUserException() {
		super("User is not authenticated");
	}
}

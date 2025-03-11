package com.speer.notes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.speer.notes.exception.UserNotFoundException;
import com.speer.notes.model.Note;
import com.speer.notes.model.User;
import com.speer.notes.repositories.NoteRepository;
import com.speer.notes.repositories.UserRepository;

@Service
public class NoteServiceImpl implements NoteService{
	
	private final NoteRepository noteRepository;
	
	private final UserRepository userRepository;

	public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
		super();
		this.noteRepository = noteRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Note> getAllNotesforUser(String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		
		return noteRepository.findByUser(user);
	}

	@Override
	public void getNotesById(Long noteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNotes(List<Note> notes, String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		
		notes.forEach(note -> note.setUser(user));
		
		noteRepository.saveAll(notes);
	}

	@Override
	public void updateNotes(Long noteId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNotes(Long noteId, String username) {
		
		
	}

	@Override
	public void shareNotes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchNotes(String keyword) {
		// TODO Auto-generated method stub
		
	}

}

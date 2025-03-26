package com.speer.notes.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speer.notes.exception.NoteNotFoundException;
import com.speer.notes.exception.UnauthorizedException;
import com.speer.notes.exception.UserNotFoundException;
import com.speer.notes.model.Note;
import com.speer.notes.model.User;
import com.speer.notes.repositories.NoteRepository;
import com.speer.notes.repositories.UserRepository;

@Service
public class NoteServiceImpl implements NoteService{
	
	private final NoteRepository noteRepository;
	
	private final UserRepository userRepository;
	
	private final ObjectMapper objectMapper;

	public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, ObjectMapper objectMapper) {
		super();
		this.noteRepository = noteRepository;
		this.userRepository = userRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public List<Note> getAllNotesforUser(String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		
		return noteRepository.findByUser(user);
	}

	@Override
	public void getNotesById(Long noteId) {
		
		
	}

	@Override
	public void createNotes(List<Note> notes, String username) {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));
		
		notes.forEach(note -> note.setUser(user));
		
		noteRepository.saveAll(notes);
	}

	/*
	 * Problem: This will unnecessary update all the fields in Note with the same
	 * value. Let's say if note has 1000 fields, it will update all 1000 with the
	 * same value or new value if provided. In order to update only the changed
	 * value, use @DynamicUpdate at Note entity class, this will detect change value
	 * and will create update query only for changed values. Other ways is you can
	 * use native query in the repository.
	 */
	@Override
	public void updateNotes(Long noteId, Map<String, Object> updates, String username) {

		Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note not found"));

		if (!note.getUser().getUsername().equals(username)) {
			throw new UnauthorizedException("Not authorized to update this note");
		}

		try {
			objectMapper.updateValue(note, updates);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		}

		noteRepository.save(note);

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

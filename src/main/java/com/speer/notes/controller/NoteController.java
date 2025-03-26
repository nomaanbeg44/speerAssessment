package com.speer.notes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.speer.notes.model.Note;
import com.speer.notes.service.NoteService;

@RestController
@RequestMapping(path = "/api/notes")
public class NoteController {

	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Note>> getAllNotes(Authentication authentication) {
		 final String username = authentication.getName();
		 List<Note> allNotesforUser = noteService.getAllNotesforUser(username);
		 return ResponseEntity.ok(allNotesforUser);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createNote(@RequestBody List<Note> notesList, Authentication authentication) {
		final String username = authentication.getName();
		noteService.createNotes(notesList, username);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteNote(@PathVariable Long id, Authentication authentication) {
		noteService.deleteNotes(id, authentication.getName());
		
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void updateNote(@PathVariable Long id, @RequestBody Map<String, Object> updates, Authentication authentication) {
		noteService.updateNotes(id, updates, authentication.getName());
	}
}

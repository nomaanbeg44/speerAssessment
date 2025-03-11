package com.speer.notes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.speer.notes.model.Note;
import com.speer.notes.service.NoteService;

@RestController
@RequestMapping(path = "/api")
public class NoteController {

	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@GetMapping(path = "/notes")
	public ResponseEntity<List<Note>> getAllNotes(Authentication authentication) {
		 String username = authentication.getName();
		 List<Note> allNotesforUser = noteService.getAllNotesforUser(username);
		 return ResponseEntity.ok(allNotesforUser);
	}
	
	@PostMapping(path = "/notes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createNote(@RequestBody List<Note> notesList, Authentication authentication) {
		String username = authentication.getName();
		noteService.createNotes(notesList, username);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteNote(@PathVariable Long id, Authentication authentication) {
		noteService.deleteNotes(id, authentication.getName());
		
	}
}

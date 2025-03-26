package com.speer.notes.service;

import java.util.List;
import java.util.Map;

import com.speer.notes.model.Note;

public interface NoteService {
	
	public List<Note> getAllNotesforUser(String username);
	
	public void getNotesById(Long noteId);
	
	public void createNotes(List<Note> notes, String username);
	
	public void updateNotes(Long noteId, Map<String, Object> updates, String username);
	
	public void deleteNotes(Long noteId, String username);
	
	public void shareNotes();
	
	public void searchNotes(String keyword);

}

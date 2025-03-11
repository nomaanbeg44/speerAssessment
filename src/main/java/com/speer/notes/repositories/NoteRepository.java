package com.speer.notes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speer.notes.model.Note;
import com.speer.notes.model.User;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByUser(User user);
}

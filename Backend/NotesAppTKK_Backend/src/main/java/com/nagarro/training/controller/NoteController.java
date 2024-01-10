package com.nagarro.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nagarro.training.model.Note;
import com.nagarro.training.model.NoteRequest;
import com.nagarro.training.service.NoteService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/notes")
public class NoteController {

	private final NoteService noteService;

	@Autowired
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/create")
	public Note createNote(@RequestBody NoteRequest noteRequest) {
		String username = noteRequest.getUsername();
		String content = noteRequest.getContent();
		return noteService.createNote(username, content);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/recent/{username}")
	public List<Note> getRecentNotes(@PathVariable String username) {
		return noteService.getRecentNotes(username);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/edit/{noteId}")
	public Note editNote(@PathVariable Long noteId, @RequestBody Note noteRequest) {
		String content = noteRequest.getContent();
		return noteService.editNote(noteId, content);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete/{noteId}")
	public void deleteNote(@PathVariable Long noteId) {
		noteService.deleteNote(noteId);
	}
}

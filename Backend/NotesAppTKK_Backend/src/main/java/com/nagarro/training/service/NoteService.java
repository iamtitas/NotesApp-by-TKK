package com.nagarro.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.model.Note;
import com.nagarro.training.model.Users;
import com.nagarro.training.repository.AuthRepository;
import com.nagarro.training.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

	private final NoteRepository noteRepository;
	private final AuthRepository authRepository;

	@Autowired
	public NoteService(NoteRepository noteRepository, AuthRepository authRepository) {
		this.noteRepository = noteRepository;
		this.authRepository = authRepository;
	}

	public Note createNote(String username, String content) {
		Users user = authRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User not found");
		}

		long timestamp = System.currentTimeMillis();
		Note note = new Note(content, user, timestamp);
		return noteRepository.save(note);
	}

	public List<Note> getRecentNotes(String username) {
		Users user = authRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("User not found");
		}

		return noteRepository.findTop10ByUserOrderByTimestampDesc(user);
	}

	public void deleteOldNotes(String username) {
		Users user = authRepository.findByUsername(username);
		if (user == null) {
			System.err.println("User not found for username: " + username);
			throw new RuntimeException("User not found");
		}

		List<Note> allUserNotes = noteRepository.findByUserOrderByTimestampDesc(user);

		System.out.println("Found " + allUserNotes.size() + " notes for user: " + username);

		if (allUserNotes.size() > 10) {

			List<Note> recentNotes = allUserNotes.subList(0, 10);

			System.out.println("Keeping the 10 most recent notes for user: " + username);

			for (Note note : allUserNotes) {
				if (!recentNotes.contains(note)) {
					noteRepository.delete(note);
				}
			}

			System.out.println("Deleted old notes for user: " + username);
		}
	}

	public Note editNote(Long noteId, String content) {
		Optional<Note> optionalNote = noteRepository.findById(noteId);
		if (!optionalNote.isPresent()) {
			throw new RuntimeException("Note not found");
		}

		Note note = optionalNote.get();
		note.setContent(content);
		return noteRepository.save(note);
	}

	public void deleteNote(Long noteId) {
		Optional<Note> optionalNote = noteRepository.findById(noteId);
		if (!optionalNote.isPresent()) {
			throw new RuntimeException("Note not found");
		}

		noteRepository.deleteById(noteId);
	}
}

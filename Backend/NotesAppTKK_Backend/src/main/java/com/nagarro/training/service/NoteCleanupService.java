package com.nagarro.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nagarro.training.repository.AuthRepository;

@Service
public class NoteCleanupService {

	private final NoteService noteService;
	private final AuthRepository authRepository;

	@Autowired
	public NoteCleanupService(NoteService noteService, AuthRepository authRepository) {
		this.noteService = noteService;
		this.authRepository = authRepository;
	}

	@Scheduled(cron = "0 0 * * * *")

	public void deleteOldNotesHourly() {

		List<String> allUsernames = authRepository.findAllUsernames();

		for (String username : allUsernames) {
			System.out.println("Processing user: " + username);

			noteService.deleteOldNotes(username);
		}
	}
}

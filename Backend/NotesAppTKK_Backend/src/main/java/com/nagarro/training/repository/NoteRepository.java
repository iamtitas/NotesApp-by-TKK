package com.nagarro.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.model.Note;
import com.nagarro.training.model.Users;

public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findTop10ByUserOrderByTimestampDesc(Users user);

	List<Note> findByUserOrderByTimestampDesc(Users user);

	void deleteByUserAndTimestampLessThan(Users user, long timestampThreshold);
}

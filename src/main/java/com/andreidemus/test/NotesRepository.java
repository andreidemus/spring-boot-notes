package com.andreidemus.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository. Is used for data persistence.
 */
@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {
}

package com.andreidemus.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Application controller. Contains all application endpoints.
 */
@RestController
@RequestMapping("/rest")
public class Controller {
    private final NotesRepository repository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public Controller(NotesRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns list of all saved notes.
     * @return all notes data in JSON format.
     */
    @RequestMapping("/notes")
    public List<Note> list() {
        return repository.findAll();
    }

    /**
     * Returns note by given id if exists, or, response with code 404, if not.
     * @param id note id.
     * @return note data in JSON format.
     */
    @RequestMapping("/notes/{id}")
    public ResponseEntity<Note> get(@PathVariable("id") Long id) {
        return Optional.ofNullable(repository.findOne(id))
                       .map(it -> new ResponseEntity<>(it, OK))
                       .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    /**
     * Adds new note.
     * @param note note data in JSON format. Author, tittle and text fields are required.
     * @return created note data in JSON format.
     * @throws IllegalArgumentException if any of required field is missing.
     */
    @RequestMapping(path = "/notes", method = POST)
    public Note add(@RequestBody Note note) {
        Assert.notNull(note.getAuthor(), "Author field cannot be empty");
        Assert.notNull(note.getTitle(), "Title field cannot be empty");
        Assert.notNull(note.getText(), "Text field cannot be empty");

        logger.info("Inserting new note with title = {}, author = {}.", note.getTitle(), note.getAuthor());

        note.setCreationDate(new Date());
        return repository.saveAndFlush(note);
    }

    /**
     * Deletes note by given id if exists. Returns 404 response code otherwise.
     * @param id node id.
     * @return response code (204 or 404).
     */
    @RequestMapping(path = "/notes/{id}", method = DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        logger.info("Removing note with id = {}.", id);

        if (!repository.exists(id)) {
            return new ResponseEntity(NOT_FOUND);
        }

        repository.delete(id);
        return new ResponseEntity(NO_CONTENT);
    }
}

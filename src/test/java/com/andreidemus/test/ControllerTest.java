package com.andreidemus.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
    @Autowired Controller controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getReturnsCorrectNote() throws Exception {
        ResponseEntity<Note> r = controller.get(1L);
        assertThat(r.getStatusCode(), is(OK));
        Note note = r.getBody();
        assertThat(note, notNullValue());
        assertThat(note.getCreationDate(), notNullValue());
        assertThat(note.getAuthor(), is("Jamie"));
        assertThat(note.getTitle(), is("Chillwave"));
        assertThat(note.getText(), startsWith("Flannel pok pok bicycle"));
    }

    @Test
    public void getReturnsNotFound_whenNoRecordWithGivenId() throws Exception {
        ResponseEntity<Note> r = controller.get(-1L);
        assertThat(r.getStatusCode(), is(NOT_FOUND));
        assertThat(r.getBody(), nullValue());
    }

    @Test
    public void listReturnsAllNotes() throws Exception {
        List<Note> notes = controller.list();
        assertThat(notes, hasSize(3));
    }

    @Test
    public void addInsertsNewNote() throws Exception {
        Note note = new Note();
        note.setAuthor("author1");
        note.setTitle("title1");
        note.setText("text1");
        note = controller.add(note);

        assertThat(note.getId(), notNullValue());
        assertThat(note.getCreationDate(), notNullValue());
        assertThat(note.getAuthor(), is("author1"));
        assertThat(note.getTitle(), is("title1"));
        assertThat(note.getText(), is("text1"));

        note = controller.get(note.getId()).getBody();

        assertThat(note.getId(), notNullValue());
        assertThat(note.getCreationDate(), notNullValue());
        assertThat(note.getAuthor(), is("author1"));
        assertThat(note.getTitle(), is("title1"));
        assertThat(note.getText(), is("text1"));
    }

    @Test
    public void addThrowsException_whenAutorNotSet() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Author field cannot be empty");

        controller.add(new Note());
    }

    @Test
    public void addThrowsException_whenTitleNotSet() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Title field cannot be empty");

        Note note = new Note();
        note.setAuthor("author1");

        controller.add(note);
    }

    @Test
    public void addThrowsException_whenTextNotSet() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Text field cannot be empty");

        Note note = new Note();
        note.setAuthor("author1");
        note.setTitle("title1");

        controller.add(note);
    }

    @Test
    public void deleteRemovesNote() throws Exception {
        Note note = new Note();
        note.setAuthor("author1");
        note.setTitle("title1");
        note.setText("text1");
        note = controller.add(note);
        Long id = note.getId();

        ResponseEntity dr = controller.delete(id);
        assertThat(dr.getStatusCode(), is(NO_CONTENT));

        ResponseEntity<Note> gr = controller.get(id);
        assertThat(gr.getStatusCode(), is(NOT_FOUND));
    }

    @Test
    public void deleteReturnsNotFound_whenNoRecordWithGivenId() throws Exception {
        ResponseEntity dr = controller.delete(-1L);
        assertThat(dr.getStatusCode(), is(NOT_FOUND));
    }
}
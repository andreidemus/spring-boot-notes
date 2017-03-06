package com.andreidemus.test;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @SequenceGenerator(name="SEQ_GEN", sequenceName="seq_notes", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_GEN")
    private Long id;

    private String author;

    private String title;

    private String text;

    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}

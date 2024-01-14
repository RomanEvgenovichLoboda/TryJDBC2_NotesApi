package com.roman.jdbch2.model;

import java.sql.Timestamp;

public class NoteModel {
    private long id;

    private String author;

    private String text;
    private Timestamp timestamp;

    public NoteModel(long id, String author, String txt, Timestamp timestamp) {
        this.id = id;
        this.author = author;
        this.text = txt;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String name) {
        this.author = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        this.text = txt;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("Note[ id = %d, Author = '%s',\nText = '%s', \nDateTime = '%s']", id, author, text, timestamp);
    }
}
package SpringJDBC_Notes.TryJDBC2.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String avtor;

    private String text;
    private LocalDateTime dateTime;

    public Note() {
    }

    public Note(String avtr, String txt, LocalDateTime dt) {
        this.avtor = avtr;
        this.text = txt;
        this.dateTime = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvtor() {
        return avtor;
    }

    public void setAvtor(String name) {
        this.avtor = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        this.text = txt;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

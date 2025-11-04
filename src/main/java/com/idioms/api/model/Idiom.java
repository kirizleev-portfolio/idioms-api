package com.idioms.api.model;

import jakarta.persistence.*;

/**
 * Represents an English idiom stored in the database.
 * Each idiom contains a text, meaning, example, and frequency score.
 */
@Entity
@Table(name = "idioms")
public class Idiom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String meaning;

    private String example;

    @Column(nullable = false)
    private int frequency;

    public Idiom() {
        // Default constructor required by JPA
    }

    // --- Getters ---
    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getExample() {
        return example;
    }

    public int getFrequency() {
        return frequency;
    }

    // --- Setters ---
    public void setText(String text) {
        this.text = text;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}

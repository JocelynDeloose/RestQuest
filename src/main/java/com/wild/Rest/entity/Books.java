package com.wild.Rest.entity;

import jakarta.persistence.*;


@Entity
public class Books {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    @Column //(nullable = false) a mettre à la fin, quand c'est callé
    private String title;
    @Column //(nullable = false)
    private String author;

    @Column //(nullable = false)
    private String description;


    public Books() {

    }



    public Books(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}

package com.example.ProjectoFinalInfo2020.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private String author;
    @Size (max = 200)
    private String comment;
    @CreationTimestamp
    private LocalDateTime fecha;

    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getAuthor() {
        return author; }

    public void setAuthor(String author) {
        this.author = author; }

    public String getComment() {
        return comment; }

    public void setComment(String comment) {
        this.comment = comment; }

    public LocalDateTime getFecha() {
        return fecha; }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

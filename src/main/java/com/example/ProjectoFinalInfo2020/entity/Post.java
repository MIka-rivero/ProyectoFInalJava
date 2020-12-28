package com.example.ProjectoFinalInfo2020.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String content;
    private Boolean published;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Usuario author;

    private LocalDate fecha = LocalDate.now() ;

    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getTitle() {
        return title; }

    public void setTitle(String title) {
        this.title = title; }

    public String getDescription() {
        return description; }

    public void setDescription(String description) {
        this.description = description; }

    public String getContent() {
        return content; }

    public void setContent(String content) {
        this.content = content; }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published; }

    public Usuario getAuthor() {
        return author; }

    public void setAuthor(Usuario author) {
        this.author = author; }

    public LocalDate getFecha() {
        return fecha; }

    public void setFecha( LocalDate fecha) {
        this.fecha = fecha; }
}

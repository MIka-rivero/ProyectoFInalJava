package com.example.ProjectoFinalInfo2020.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String apellido;
    @Column (unique = true)
    private String email;
    @JsonIgnore
    private Long password;
    private String ciudad;
    private String provincia;
    private String pais;
    private LocalDate date = LocalDate.now();

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    public Long getId() {
        return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name; }

    public String getApellido() {
        return apellido; }

    public void setApellido(String apellido) {
        this.apellido = apellido; }

    public String getEmail() {
        return email; }

    public void setEmail(String email) {
        this.email = email; }

    public Long getPassword() {
        return password; }

    public void setPassword(Long password) {
        this.password = password; }

    public String getCiudad() {
        return ciudad; }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad; }

    public String getProvincia() {
        return provincia; }

    public void setProvincia(String provincia) {
        this.provincia = provincia; }

    public String getPais() {
        return pais; }

    public void setPais(String pais) {
        this.pais = pais; }

    public LocalDate getFecha() {
        return date; }

    public void setFecha(LocalDate date) {
        this.date = date; }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setAuthor(this);
    }

}

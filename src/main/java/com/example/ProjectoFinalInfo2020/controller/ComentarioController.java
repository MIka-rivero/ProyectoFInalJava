package com.example.ProjectoFinalInfo2020.controller;

import com.example.ProjectoFinalInfo2020.entity.Comentario;

import com.example.ProjectoFinalInfo2020.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    //crear comentario
    @PostMapping
    public ResponseEntity<?>crearComentario(@RequestBody Comentario comentario) {
        return new ResponseEntity<>(comentarioService.save(comentario), HttpStatus.CREATED);
    }

    //obtener todos los comentarios
    @GetMapping()
    public ResponseEntity<?> getComentarios() {
        return new ResponseEntity<>(comentarioService.findAll(), HttpStatus.OK);
    }


    //modificar un comentario segun id
    @PutMapping("/{comentarioId}")
    public ResponseEntity<?> editComentario (@RequestBody Comentario comentariosDetails, @PathVariable Long comentarioId) {
        Optional<Comentario> comentario = comentarioService.findById(comentarioId);
        if(!comentario.isPresent()){
            return ResponseEntity.notFound().build();
        }
        comentario.get().setAuthor(comentariosDetails.getAuthor());
        comentario.get().setComment(comentariosDetails.getComment());
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.save(comentario.get()));
    }

    //borrar un comentario segun id
    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<?> deleteComentario (@PathVariable Long comentarioId) {
        comentarioService.deleteById(comentarioId);
        return  ResponseEntity.ok().build();
    }

}

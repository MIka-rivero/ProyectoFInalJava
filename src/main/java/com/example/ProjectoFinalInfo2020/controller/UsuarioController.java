package com.example.ProjectoFinalInfo2020.controller;

import com.example.ProjectoFinalInfo2020.entity.Post;
import com.example.ProjectoFinalInfo2020.entity.Usuario;
import com.example.ProjectoFinalInfo2020.service.PostService;
import com.example.ProjectoFinalInfo2020.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuario")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostService postService;

    //crear nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));}

   //obtener todos los usuarios
   @GetMapping()
    public ResponseEntity<?> getUsuario() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK); }

   @GetMapping("/searchCiudad")
    public ResponseEntity<?> usuarioPorCiudad (@RequestParam String ciudad) {
        List<Usuario> usuario= usuarioService.findByCiudad(ciudad);
       return new ResponseEntity<>(usuario, HttpStatus.OK); }

   @GetMapping("/searchFecha")
   public ResponseEntity<?> usuarioPorFecha (@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Usuario> usuario = usuarioService.findByDate(date);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
   }


    //modificar un usuario segun id
    @PutMapping("/usuario")
    public ResponseEntity<?> editUsuario (@RequestBody Usuario usuarioDetails, @PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.get().setName(usuarioDetails.getName());
        usuario.get().setApellido(usuarioDetails.getApellido());
        usuario.get().setEmail(usuarioDetails.getEmail());
        usuario.get().setPassword(usuarioDetails.getPassword());
        usuario.get().setCiudad(usuarioDetails.getCiudad());
        usuario.get().setProvincia(usuarioDetails.getProvincia());
        usuario.get().setPais(usuarioDetails.getPais());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));

    }

    //borrar un usuario segun id
    @DeleteMapping("/{usuarioId}")
    public  ResponseEntity<?> deleteUsuario (@PathVariable Long usuarioId) {
        usuarioService.deleteById(usuarioId);
        return ResponseEntity.ok().build();
    }



    //crear un nuevo post
    @PostMapping("/{usuarioId}/post")
    public ResponseEntity<?> crearPostUsuario (@PathVariable Long usuarioId, @RequestBody Post postDetails) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuario.get().addPost(postDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
    }

}

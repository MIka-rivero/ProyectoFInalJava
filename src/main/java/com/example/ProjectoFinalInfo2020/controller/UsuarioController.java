package com.example.ProjectoFinalInfo2020.controller;

import com.example.ProjectoFinalInfo2020.entity.Usuario;
import com.example.ProjectoFinalInfo2020.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuario")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //crear nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));}

   //obtener todos los usuarios
   @GetMapping()
    public ResponseEntity<?> getUsuario() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK); }

   // @GetMapping("/search")
    //public ResponseEntity<?> usuarioPorCiudad (@RequestParam String ciudad) {
        //List<Usuario> usuario= usuarioService.findByCiudad(ciudad);
       //return new ResponseEntity<>(usuario, HttpStatus.OK);
    //}

    @GetMapping("/buscar")
    public ResponseEntity<?> usuarioPorFecha (@RequestParam LocalDateTime fecha) {
        List<Usuario> usuarioFecha = usuarioService.findByDate(fecha);
        return new ResponseEntity<>(usuarioFecha, HttpStatus.OK);
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

}

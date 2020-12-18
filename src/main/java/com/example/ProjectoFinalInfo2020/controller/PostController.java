package com.example.ProjectoFinalInfo2020.controller;

import com.example.ProjectoFinalInfo2020.entity.Post;

import com.example.ProjectoFinalInfo2020.entity.Usuario;
import com.example.ProjectoFinalInfo2020.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    //obtener todos los post
    @GetMapping()
    public ResponseEntity<?> getPost() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> postPorTitulo (@RequestParam String title) {
        List<Post> postTitulo = postService.findByTitle(title);
        return new ResponseEntity<>(postTitulo, HttpStatus.OK);
    }
    //crear post
    @PostMapping
    public ResponseEntity<?> crearPost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post)) ;}

    //modificar post por id
    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@RequestBody Post postDetails, @PathVariable Long postId) {
        Optional<Post> post = postService.findById(postId);
        if (!post.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        post.get().setTitle(postDetails.getTitle());
        post.get().setDescription(postDetails.getDescription());
        post.get().setContent(postDetails.getContent());
        post.get().setPublished(postDetails.getPublished());
        post.get().setAuthor(postDetails.getAuthor());
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post.get()));
    }

    //borrar post segun id
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.ok().build();
    }
}

package com.example.ProjectoFinalInfo2020.repository;

import com.example.ProjectoFinalInfo2020.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
}

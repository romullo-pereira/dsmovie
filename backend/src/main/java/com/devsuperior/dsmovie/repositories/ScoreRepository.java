package com.devsuperior.dsmovie.repositories;

import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

    @Query("select s from Score s " +
            "where s.id.movie.id = ?1")
    List<Score> findByMovieId(Long id);


}

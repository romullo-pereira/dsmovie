package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.devsuperior.dsmovie.utils.ResponseMessages.NON_EXISTENT_MOVIE;
import static com.devsuperior.dsmovie.utils.ResponseMessages.NO_CONTENT;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> getAll(Pageable pageable) throws Exception {
        Page<Movie> result =  repository.findAll(pageable);
        if(result.isEmpty()) {
            throw new Exception(NO_CONTENT);
        }
        return result.map(MovieDTO::new);
    }

    public MovieDTO getById(Long id) throws Exception {
        Optional<Movie> result = repository.findById(id);
        if(!result.isPresent()) {
            throw new Exception(NON_EXISTENT_MOVIE);
        }
        return new MovieDTO(result.get());
    }
}

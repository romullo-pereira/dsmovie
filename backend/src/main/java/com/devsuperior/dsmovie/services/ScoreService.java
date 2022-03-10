package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.ScorePK;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.devsuperior.dsmovie.utils.Helpers.avgValues;
import static com.devsuperior.dsmovie.utils.ResponseMessages.NON_EXISTENT_MOVIE;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieDTO saveScore(ScoreDTO dto) throws Exception {
        ScorePK scorePK = new ScorePK(getMovieById(dto.getId()), getUserByEmail(dto.getEmail()));

        updateScore(scorePK.getMovie(), scoreRepository.findByMovieId(dto.getId()));

        return new MovieDTO(scoreRepository.saveAndFlush(new Score(scorePK, dto.getValue())).getId().getMovie());
    }

    private User getUserByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if(!userOptional.isPresent()) {
            return userRepository.saveAndFlush(new User(email));
        }

        return userOptional.get();
    }

    private Movie getMovieById(Long id) throws Exception {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if(!movieOptional.isPresent()) {
            throw new Exception(NON_EXISTENT_MOVIE);
        }

        return movieOptional.get();
    }

    private void updateScore(Movie movie, List<Score> scores) {

        movie.setScore(0.0);

        if(!scores.isEmpty()) {
            movie.setCount(scores.size());
            movie.setScore(avgValues(scores));
        }
        movieRepository.save(movie);
    }
}

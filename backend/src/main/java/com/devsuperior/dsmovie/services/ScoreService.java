package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.ScorePK;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;
import com.devsuperior.dsmovie.utils.Helpers;
import com.devsuperior.dsmovie.utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.devsuperior.dsmovie.utils.Helpers.sumValues;
import static com.devsuperior.dsmovie.utils.ResponseMessages.NON_EXISTENT_MOVIE;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Score saveScore(ScoreDTO dto) throws Exception {
        ScorePK scorePK = new ScorePK(getMovieById(dto.getId()), getUserByEmail(dto.getEmail()));

        updateScore(scorePK.getMovie(), scoreRepository.findByMovieId(dto.getId()));

        return scoreRepository.save(new Score(scorePK, dto.getValue()));
    }

    private User getUserByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if(!userOptional.isPresent()) {
            return userRepository.save(new User(email));
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
            movie.setCount(movie.getCount() + 1);
            movie.setScore(sumValues(scores) / movie.getCount());
        }
        movieRepository.save(movie);
    }
}

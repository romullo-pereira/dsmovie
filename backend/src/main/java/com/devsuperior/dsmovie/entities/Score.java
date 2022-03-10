package com.devsuperior.dsmovie.entities;

import com.devsuperior.dsmovie.utils.ResponseMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_score")
public class Score {

    @EmbeddedId
    private ScorePK id = new ScorePK();

    @Min(value = 1, message = ResponseMessages.INVALID_VALUE)
    @Max(value = 5, message = ResponseMessages.INVALID_VALUE)
    private Double value;

    public Score() {}

    public Score(ScorePK id, Double value) {
        this.id = id;
        this.value = value;
    }

    public ScorePK getId() {
        return id;
    }

    public void setId(ScorePK id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setMovie(Movie movie) {
        id.setMovie(movie);
    }

    public void setUser(User user) {
        id.setUser(user);
    }

}

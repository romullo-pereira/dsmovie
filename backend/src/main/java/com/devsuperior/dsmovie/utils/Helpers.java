package com.devsuperior.dsmovie.utils;

import com.devsuperior.dsmovie.entities.Score;

import java.util.List;

public class Helpers {

    public static Double sumValues(List<Score> scores) {
        return scores.stream().mapToDouble(Score::getValue).sum();
    }
}

package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {

    @Autowired
    private ScoreService service;

    @PostMapping
    public ResponseEntity saveScore(@Valid @RequestBody ScoreDTO scoreDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveScore(scoreDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.devsuperior.dsmovie.dto;

public class ScoreDTO {

    private Long id;
    private String email;
    private Double value;

    public ScoreDTO() {}

    public ScoreDTO(Long id, String email, Double value) {
        this.id = id;
        this.email = email;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

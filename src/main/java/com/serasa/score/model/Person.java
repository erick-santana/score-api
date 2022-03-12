package com.serasa.score.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty(message = "The name of person cannot be empty")
    @JsonProperty("nome")
    private String name;

    @NotNull
    @NotEmpty(message = "The phone of person cannot be empty")
    @JsonProperty("telefone")
    @Size(min = 11, max = 13)
    private String phone;

    @NotNull
    @Min(value = 1, message = "The age field must be greater than or equal to one")
    @Max(value = 150, message = "The age field must be less than or equal to two hundred")
    @JsonProperty("idade")
    private int age;

    @NotNull
    @NotEmpty(message = "The city of person cannot be empty")
    @JsonProperty("cidade")
    private String city;

    @NotNull
    @NotEmpty(message = "The district of person cannot be empty")
    @JsonProperty("estado")
    @Size(min = 2, max = 2)
    private String district;

    @NotNull
    @Min(value = 0, message = "The score field must be greater than or equal to zero")
    @Max(value = 1000, message = "The score field must be less than or equal to one thousand")
    @JsonProperty("score")
    private int score;
}

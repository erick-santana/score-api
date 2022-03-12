package com.serasa.score.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponse {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("telefone")
    private String phone;
    @JsonProperty("idade")
    private int age;
    @JsonProperty("scoreDescricao")
    private String scoreDescription;
}

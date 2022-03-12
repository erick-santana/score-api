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
public class PersonLocationResponse {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String district;
    @JsonProperty("scoreDescricao")
    private String scoreDescription;
}

package com.innter.msdesigngarmentmaterial.dtos.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignCompositionResponse {

    @JsonProperty("colorId")
    private Long id;

    @JsonProperty("name")
    private String name;
}

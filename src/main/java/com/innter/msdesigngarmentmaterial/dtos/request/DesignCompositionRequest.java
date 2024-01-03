package com.innter.msdesigngarmentmaterial.dtos.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignCompositionRequest {

    @JsonProperty("colorId")
    private Long id;
}

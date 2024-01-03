package com.innter.msdesigngarmentmaterial.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderResponse {

    @JsonProperty("providerId")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("providerKey")
    private String providerKey;
}

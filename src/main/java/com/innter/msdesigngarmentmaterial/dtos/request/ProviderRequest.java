package com.innter.msdesigngarmentmaterial.dtos.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("providerKey")
    private String providerKey;
}

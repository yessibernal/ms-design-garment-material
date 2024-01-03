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
public class DesignMaterialClothDetailRequest {

    @JsonProperty("color")
    private String color;

    @JsonProperty("codeColor")
    private String codeColor;

    @JsonProperty("indication")
    private String indication;

    @JsonProperty("imageName")
    private String image;

    @JsonProperty("imagePath")
    private String imagePath;
}

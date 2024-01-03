package com.innter.msdesigngarmentmaterial.dtos.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.innter.msdesigngarmentmaterial.dtos.DesignCompositionDto;
import com.innter.msdesigngarmentmaterial.dtos.DesignGarmentGroupDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignMaterialClothResponse {

    @JsonProperty("designMaterialClothId")
    private Long id;

    @JsonProperty("designCompositions")
    private List<DesignCompositionDto> designComposition;

    @JsonProperty("name")
    private String name;

    @JsonProperty("designCompositionGroupId")
    private Long designCompositionGroupId;

    @JsonProperty("designGarmentsGroup")
    private List<DesignGarmentGroupDto> designGarmentGroup;

    @JsonProperty("width")
    private int width;

    @JsonProperty("meterPrice")
    private BigDecimal meterPrice;

    @JsonProperty("designPrintId")
    private Long designPrintId;

    @JsonProperty("designMaterialClothDetailId")
    private Long designMaterialClothDetailId;

    @JsonProperty("providerId")
    private Long providerId;
}

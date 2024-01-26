package com.innter.msdesigngarmentmaterial.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignMaterialClothEditedDto {

    @JsonProperty("designMaterialClothId")
    private Long id;

    @JsonProperty("designCompositions")
        private List<DesignCompositionDto> designComposition;

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

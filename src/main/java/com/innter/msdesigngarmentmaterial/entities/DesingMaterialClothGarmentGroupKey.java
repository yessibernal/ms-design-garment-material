package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DesingMaterialClothGarmentGroupKey {

    @Column(name = "fi_id_material_cloths", nullable = false)
    private Long idMaterialClothsGarment;

    @Column(name = "fi_id_garment_group", nullable = false)
    private Long idGarmentGroup;
}

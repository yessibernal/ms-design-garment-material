package com.innter.msdesigngarmentmaterial.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DesingMaterialClothCompositionKey {

    @Column(name = "fi_id_material_cloths", nullable = false)
    private Long idMaterialCloths;

    @Column(name = "fi_id_compositions", nullable = false)
    private Long idCompositions;
}

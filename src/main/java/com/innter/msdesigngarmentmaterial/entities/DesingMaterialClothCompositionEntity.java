package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_design_materials_cloths_compositions")
public class DesingMaterialClothCompositionEntity {

    @EmbeddedId
    private DesingMaterialClothCompositionKey id = new DesingMaterialClothCompositionKey();

    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("idCompositions")
    @JoinColumn(name = "fi_id_compositions")
    private DesignCompositionEntity designComposition;

    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("idMaterialCloths")
    @JoinColumn(name = "fi_id_material_cloths")
    private DesignMaterialClothEntity designMaterialClothComposition;

    @Column(name = "fi_percent")
    private int percent;

}

package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_design_materials_cloths_garments_group")
public class DesingMaterialClothGarmentGroupEntity {

    @EmbeddedId
    private DesingMaterialClothGarmentGroupKey id = new DesingMaterialClothGarmentGroupKey();

    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("idMaterialClothsGarment")
    @JoinColumn(name = "fi_id_material_cloths")
    private DesignMaterialClothEntity designMaterialClothGarment;


    @ManyToOne(fetch= FetchType.LAZY)
    @MapsId("idGarmentGroup")
    @JoinColumn(name = "fi_id_garment_group")
    private DesignGarmentGroupEntity designGarmentGroup;
}


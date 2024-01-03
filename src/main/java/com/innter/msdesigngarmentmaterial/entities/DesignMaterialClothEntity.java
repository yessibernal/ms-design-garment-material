package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "tb_design_materials_cloths")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignMaterialClothEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fi_composition_group")
    private DesignCompositionGroupEntity compositionGroup;

    @Column(name = "fi_width")
    private int width;

    @Column(name = "fd_meter_price")
    private BigDecimal meterPrice;

    @ManyToOne
    @JoinColumn(name = "fi_print")
    private DesignPrintEntity print;

    @ManyToOne
    @JoinColumn(name = "fi_cloth_detail")
    private DesignMaterialClothDetailEntity clothDetail;

    @ManyToOne
    @JoinColumn(name = "fi_provider")
    private ProviderEntity provider;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "designMaterialClothComposition", cascade= CascadeType.ALL)
    private Set<DesingMaterialClothCompositionEntity> designMaterialComposition;

    @OneToMany(mappedBy = "designMaterialClothGarment", cascade= CascadeType.ALL)
    private Set<DesingMaterialClothGarmentGroupEntity> designMaterialGarment;
}

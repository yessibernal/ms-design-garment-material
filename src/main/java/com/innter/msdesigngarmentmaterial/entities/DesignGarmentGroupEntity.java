package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "tb_design_garments_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DesignGarmentGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fc_code")
    private String code;

    @Column(name = "fc_garment_location")
    private String provider;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "designGarmentGroup", cascade= CascadeType.ALL)
    private Set<DesingMaterialClothGarmentGroupEntity> designMaterialGarment;
}

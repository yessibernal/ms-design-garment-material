package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "tb_design_compositions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DesignCompositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "designComposition", cascade= CascadeType.ALL)
    private Set<DesingMaterialClothCompositionEntity> designMaterialComposition;
}

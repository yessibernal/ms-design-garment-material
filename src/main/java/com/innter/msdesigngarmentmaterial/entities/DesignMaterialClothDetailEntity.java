package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_design_materials_cloths_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignMaterialClothDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_color")
    private String color;

    @Column(name = "fc_code_color")
    private String codeColor;

    @Column(name = "fc_indication")
    private String indication;

    @Column(name = "fc_image_name")
    private String image;

    @Column(name = "ft_image_path")
    private String imagePath;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "clothDetail")
    Set<DesignMaterialClothEntity> designMaterialCloth;

}

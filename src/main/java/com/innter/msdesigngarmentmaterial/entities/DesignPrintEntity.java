package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_design_print")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DesignPrintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fb_status")
    private Boolean status = true;

    @OneToMany(mappedBy = "print")
    Set<DesignMaterialClothEntity> designMaterialCloth;
}

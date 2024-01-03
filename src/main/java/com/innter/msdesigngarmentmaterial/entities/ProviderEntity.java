package com.innter.msdesigngarmentmaterial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_providers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_id")
    private Long id;

    @Column(name = "fc_name")
    private String name;

    @Column(name = "fc_provider_key")
    private String providerKey;

    @Column(name = "fb_status")
    private Boolean status ;

    @OneToMany(mappedBy = "provider")
    Set<DesignMaterialClothEntity> designMaterialCloth;
}

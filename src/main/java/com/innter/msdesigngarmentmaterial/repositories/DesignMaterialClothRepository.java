package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMaterialClothRepository extends JpaRepository <DesignMaterialClothEntity, Long> {
    @Query("SELECT m FROM DesignMaterialClothEntity m WHERE m.status = true")
    List<DesignMaterialClothEntity> findDesignMaterialClothEntity(Pageable pageable);

    @Query("SELECT m FROM DesignMaterialClothEntity m WHERE m.id = :id AND m.status = true")
    DesignMaterialClothEntity findDesignMaterialClothStatusById(Long id);

}


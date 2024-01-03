package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesignMaterialClothDetailEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMaterialClothDetailRepository extends JpaRepository <DesignMaterialClothDetailEntity, Long> {

    @Query("SELECT m FROM DesignMaterialClothDetailEntity m WHERE m.id = :id AND m.status = true")
    DesignMaterialClothDetailEntity findDesignMaterialClothDetail(Long id);

    @Query("SELECT m FROM DesignMaterialClothDetailEntity m")
    List<DesignMaterialClothDetailEntity> findDesignMaterialCloth(Pageable pageable);
}

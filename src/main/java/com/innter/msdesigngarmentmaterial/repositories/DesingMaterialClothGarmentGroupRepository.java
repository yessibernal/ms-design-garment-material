package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesingMaterialClothGarmentGroupEntity;
import com.innter.msdesigngarmentmaterial.entities.DesingMaterialClothGarmentGroupKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesingMaterialClothGarmentGroupRepository extends JpaRepository<DesingMaterialClothGarmentGroupEntity, DesingMaterialClothGarmentGroupKey> {

    @Query("SELECT d FROM DesingMaterialClothGarmentGroupEntity d WHERE d.designMaterialClothGarment.id = :id")
    List<DesingMaterialClothGarmentGroupEntity> findDesignMaterialClothGarment (Long id);
}

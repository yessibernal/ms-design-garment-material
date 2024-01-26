package com.innter.msdesigngarmentmaterial.repositories;
import com.innter.msdesigngarmentmaterial.entities.DesingMaterialClothCompositionEntity;
import com.innter.msdesigngarmentmaterial.entities.DesingMaterialClothCompositionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMaterialClothCompositionRepository extends JpaRepository<DesingMaterialClothCompositionEntity, DesingMaterialClothCompositionKey> {

    @Query("SELECT d FROM DesingMaterialClothCompositionEntity d WHERE d.designMaterialClothComposition.id = :id")
   List<DesingMaterialClothCompositionEntity>  findDesingMaterialClothComposition (Long id);



}

package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesignCompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignCompositionRepository extends JpaRepository<DesignCompositionEntity, Long> {

}

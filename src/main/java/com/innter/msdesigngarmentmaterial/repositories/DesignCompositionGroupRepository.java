package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesignCompositionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignCompositionGroupRepository extends JpaRepository<DesignCompositionGroupEntity, Long> {

    @Query("SELECT c FROM DesignCompositionGroupEntity c WHERE c.id = :id AND c.status = true")
    DesignCompositionGroupEntity findDesignCompositionGroup(Long id);
}

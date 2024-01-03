package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesignGarmentGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignGarmentGroupRepository extends JpaRepository<DesignGarmentGroupEntity,Long> {
}

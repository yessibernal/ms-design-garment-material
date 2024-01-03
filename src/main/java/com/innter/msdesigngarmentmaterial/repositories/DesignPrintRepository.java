package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.DesignPrintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignPrintRepository  extends JpaRepository<DesignPrintEntity, Long> {

    @Query("SELECT p FROM DesignPrintEntity p WHERE p.id = :id AND p.status = true")
    DesignPrintEntity findDesignPrint(Long id);
}

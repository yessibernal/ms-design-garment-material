package com.innter.msdesigngarmentmaterial.repositories;

import com.innter.msdesigngarmentmaterial.entities.ProviderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

    @Query("SELECT p FROM ProviderEntity p WHERE p.status = true")
    List<ProviderEntity> findProviders (Pageable pageable);
    @Query("SELECT p FROM ProviderEntity p WHERE p.id = :id AND p.status = true")
    ProviderEntity findProviderStatusById (Long id);

}

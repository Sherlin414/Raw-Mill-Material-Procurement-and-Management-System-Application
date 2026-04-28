package com.example.rawmillmanagement.repository;

import com.example.rawmillmanagement.entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
    Optional<MaterialEntity> findByMaterialId(String materialId);
    boolean existsByMaterialId(String materialId);
}

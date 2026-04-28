package com.example.rawmillmanagement.repository;

import com.example.rawmillmanagement.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findBySupplierId(String supplierId);
    boolean existsBySupplierId(String supplierId);
}

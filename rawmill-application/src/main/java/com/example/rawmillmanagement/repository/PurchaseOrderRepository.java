package com.example.rawmillmanagement.repository;

import com.example.rawmillmanagement.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long> {
    Optional<PurchaseOrderEntity> findByOrderId(String orderId);
    boolean existsByOrderId(String orderId);
}

package com.example.orderservicemq.repository;

import com.example.orderservicemq.entity.Bread;
import com.example.orderservicemq.model.type.BreadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreadRepository extends JpaRepository<Bread, Long> {
    Optional<Bread> findByType(BreadType type);
}

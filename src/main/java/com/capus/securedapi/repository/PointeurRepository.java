package com.capus.securedapi.repository;

import com.capus.securedapi.entity.Pointer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointeurRepository extends JpaRepository<Pointer, Long> {
}

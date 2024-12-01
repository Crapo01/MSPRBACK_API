package com.capus.securedapi.repository;

import com.capus.securedapi.entity.Pointeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointeurRepository extends JpaRepository<Pointeur, Long> {
}

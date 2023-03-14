package com.github.andregpereira.resilientshop.userapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.andregpereira.resilientshop.userapi.entities.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}

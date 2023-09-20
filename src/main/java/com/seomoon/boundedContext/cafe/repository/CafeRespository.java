package com.seomoon.boundedContext.cafe.repository;

import com.seomoon.boundedContext.cafe.model.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRespository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByCafeName(String cafeName);

}

package com.seomoon.boundedContext.cafe.repository;

import com.seomoon.boundedContext.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRespository extends JpaRepository<Cafe, Long> {
}

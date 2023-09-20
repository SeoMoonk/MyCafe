package com.seomoon.cafe.repository;

import com.seomoon.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRespository extends JpaRepository<Cafe, Long> {
}

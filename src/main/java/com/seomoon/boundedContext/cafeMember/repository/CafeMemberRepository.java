package com.seomoon.boundedContext.cafeMember.repository;

import com.seomoon.boundedContext.cafeMember.model.entity.CafeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeMemberRepository extends JpaRepository<CafeMember, Long> {
}

package com.ly.txbehavior.repository;

import com.ly.txbehavior.entity.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRepository extends JpaRepository<Count,Long> {
}

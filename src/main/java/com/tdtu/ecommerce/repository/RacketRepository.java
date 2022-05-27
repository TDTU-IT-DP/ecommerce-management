package com.tdtu.ecommerce.repository;

import com.tdtu.ecommerce.entity.Racket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RacketRepository extends JpaRepository<Racket, Long> {
    List<Racket> findByActiveTrue();
    boolean existsById(Long id);
}

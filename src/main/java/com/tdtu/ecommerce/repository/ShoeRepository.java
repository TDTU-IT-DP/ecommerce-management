package com.tdtu.ecommerce.repository;

import com.tdtu.ecommerce.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {

    List<Shoe> findByActiveIsTrue();
}

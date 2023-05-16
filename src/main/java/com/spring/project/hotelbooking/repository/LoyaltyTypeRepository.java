package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoyaltyTypeRepository extends JpaRepository<Loyalty,Integer> {

    @Query("FROM Loyalty LO WHERE LO.name = :name ")
    Optional<Loyalty> getLoyaltyTypeByName(@Param("name")String name);
}

package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Integer> {
}

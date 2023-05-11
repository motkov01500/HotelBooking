package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.GuestReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestReviewRepository extends JpaRepository<GuestReview,Integer> {
}

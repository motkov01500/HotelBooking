package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.entity.Facility;
import com.spring.project.hotelbooking.exception.FacilityNotFoundException;
import com.spring.project.hotelbooking.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Facility getFacilityById(int id) {
        return facilityRepository
                .findById(id)
                .orElseThrow(()->new FacilityNotFoundException("That facility is not found!"));
    }
}

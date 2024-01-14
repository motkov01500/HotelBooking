package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.entity.Loyalty;
import com.spring.project.hotelbooking.exception.LoyaltyTypeNotFoundException;
import com.spring.project.hotelbooking.repository.LoyaltyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService {

    private final LoyaltyTypeRepository loyaltyTypeRepository;

    @Autowired
    public LoyaltyService(LoyaltyTypeRepository loyaltyTypeRepository) {
        this.loyaltyTypeRepository = loyaltyTypeRepository;
    }

    public Loyalty getLoyaltyTypeByName(String loyaltyTypeName) {
        Loyalty loyaltyType = loyaltyTypeRepository
                .getLoyaltyTypeByName(loyaltyTypeName)
                .orElseThrow(()-> new LoyaltyTypeNotFoundException("This loyalty type is not found!"));
        return loyaltyType;
    }
}

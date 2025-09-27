package com.unidost.unidostbe.Service;


import com.unidost.unidostbe.Entity.Plan;
import com.unidost.unidostbe.Repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public List<Plan> getApartmentsBySchoolNameAndType(String schoolName, String type) {
        return planRepository.findBySchoolNameAndTypeOrderByPostedAtDesc(schoolName, type); // New service method
    }
}
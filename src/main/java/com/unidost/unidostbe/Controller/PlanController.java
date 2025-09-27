package com.unidost.unidostbe.Controller;


import com.unidost.unidostbe.Entity.Plan;
import com.unidost.unidostbe.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin(origins = "*")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planService.savePlan(plan);
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("{schoolName}/type/{type}")
    public List<Plan> getPlansByType(@PathVariable String schoolName,@PathVariable String type) {
        return planService.getApartmentsBySchoolNameAndType(schoolName,type);
    }
}

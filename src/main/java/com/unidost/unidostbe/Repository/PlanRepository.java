package com.unidost.unidostbe.Repository;

import com.unidost.unidostbe.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findBySchoolNameAndType(String schoolName, String type);

    List<Plan> findBySchoolNameAndTypeOrderByPostedAtDesc(String schoolName, String type);



}

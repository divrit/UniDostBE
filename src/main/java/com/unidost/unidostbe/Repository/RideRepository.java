package com.unidost.unidostbe.Repository;

import com.unidost.unidostbe.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {


    List<Ride> findBySchoolName(String schoolName);
    List<Ride> findAllByOrderByPostedAtDesc();

    List<Ride> findBySchoolNameOrderByPostedAtDesc(String schoolName);
}
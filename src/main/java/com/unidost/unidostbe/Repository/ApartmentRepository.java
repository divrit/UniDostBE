package com.unidost.unidostbe.Repository;

import com.unidost.unidostbe.Entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findBySchoolNameAndType(String schoolName, String type);

    List<Apartment> findBySchoolNameAndTypeOrderByPostedAtDesc(String schoolName, String type);


}

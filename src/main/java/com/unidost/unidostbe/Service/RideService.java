package com.unidost.unidostbe.Service;

import com.unidost.unidostbe.Entity.Ride;
import com.unidost.unidostbe.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {


    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> getRidesBySchoolName(String schoolName) {
        return rideRepository.findBySchoolNameOrderByPostedAtDesc(schoolName);
    }
}


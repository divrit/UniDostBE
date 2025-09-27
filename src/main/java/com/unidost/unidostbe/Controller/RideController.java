package com.unidost.unidostbe.Controller;


import com.unidost.unidostbe.Entity.Ride;
import com.unidost.unidostbe.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @PostMapping
    public Ride saveRide(@RequestBody Ride ride) {
        return rideService.saveRide(ride);
    }

    @GetMapping("/school/{schoolName}")
    public List<Ride> getRidesBySchoolName(@PathVariable String schoolName) {
        return rideService.getRidesBySchoolName(schoolName);
    }

}

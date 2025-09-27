package com.unidost.unidostbe.Service;

import com.unidost.unidostbe.Entity.Apartment;
import com.unidost.unidostbe.Entity.User;
import com.unidost.unidostbe.Repository.ApartmentRepository;
import com.unidost.unidostbe.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private UserRepository userRepository;
    public Apartment saveApartment(Apartment apartment, String username) {
        User user = userRepository.findByUsername(username);

//            // Throw an exception if the user is not found
            if (user == null) {
                // If the user is not found, create a new user
                user = new User(username);
                user = userRepository.save(user);
        }

        apartment.setUser(user); // Associate the apartment with the user
        return apartmentRepository.save(apartment);
    }



    public List<Apartment> getApartmentsBySchoolNameAndType(String schoolName, String type) {
        return apartmentRepository.findBySchoolNameAndTypeOrderByPostedAtDesc(schoolName, type);
    }
}

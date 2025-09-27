package com.unidost.unidostbe.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.unidost.unidostbe.Entity.Apartment;
import com.unidost.unidostbe.Service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/apartments")
@CrossOrigin(origins = "*")
public class ApartmentController {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private ApartmentService apartmentService;


    @GetMapping("/test")
    public String test() {
        return "working";
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveApartmentWithImages(
            @RequestParam("username") String username,
            @RequestPart("apartment") Apartment apartment,
            @RequestPart("files") List<MultipartFile> files) {

        List<String> fileUrls = new ArrayList<>();

        try {
            for (MultipartFile file : files) {

                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());
                metadata.setContentDisposition("inline");

                amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                        );


                String fileUrl = amazonS3.getUrl(bucketName, fileName).toString();
                fileUrls.add(fileUrl);
            }
            apartment.setImages(fileUrls);

            Apartment savedApartment = apartmentService.saveApartment(apartment, username);

            return new ResponseEntity<>(savedApartment, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading files: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/school/{schoolName}/type/{type}")
    public ResponseEntity<List<Apartment>> getApartmentsBySchoolNameAndType(@PathVariable String schoolName, @PathVariable String type) {
        List<Apartment> apartments = apartmentService.getApartmentsBySchoolNameAndType(schoolName, type);
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }





}

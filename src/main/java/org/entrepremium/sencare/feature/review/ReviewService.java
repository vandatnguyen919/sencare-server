package org.entrepremium.sencare.feature.review;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.DoctorService;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.HospitalService;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hosserv.HosServService;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.UserService;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;
    private final HosServService hosServService;
    private final ReviewRepository reviewRepository;

    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Page<Review> findByHospitalId(Pageable pageable, String hospitalId) {
        Hospital hospital = hospitalService.findById(hospitalId);
        return reviewRepository.findByHospital(pageable, hospital);
    }

    public Page<Review> findByDoctorId(Pageable pageable, String doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        return reviewRepository.findByDoctor(pageable, doctor);
    }

    public Page<Review> findByHosServId(Pageable pageable, String hosServId) {
        HosServ hosServ = hosServService.findById(hosServId);
        return reviewRepository.findByHosServ(pageable, hosServ);
    }

    public Review findById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
    }

    public Review save(Review newReview) {
        return reviewRepository.save(newReview);
    }

    public Review createHosServReview(String userId, String hosServId, Review review) {
        MyUser user = userService.findById(userId);
        HosServ hosServ = hosServService.findById(hosServId);

        review.setCreatedBy(user);
        review.setHosServ(hosServ);
        review.setHospital(hosServ.getHospital());
        return reviewRepository.save(review);
    }

    public Review createDoctorReview(String userId, String doctorId, Review review) {
        MyUser user = userService.findById(userId);
        Doctor doctor = doctorService.findById(doctorId);

        review.setCreatedBy(user);
        review.setDoctor(doctor);
        review.setHospital(doctor.getHospital());
        return reviewRepository.save(review);
    }

    public Review update(String reviewId, Review review) {
        return reviewRepository.findById(reviewId)
                .map(oldReview -> {
                    oldReview.setContent(review.getContent());
                    oldReview.setRating(review.getRating());
                    return reviewRepository.save(oldReview);
                })
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
    }

    public void delete(String reviewId) {
        this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ObjectNotFoundException("review", reviewId));
        this.reviewRepository.deleteById(reviewId);
    }
}
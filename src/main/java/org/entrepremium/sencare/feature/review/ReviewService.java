package org.entrepremium.sencare.feature.review;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.doctorsystem.doctor.DoctorService;
import org.entrepremium.sencare.feature.hospitalsystem.hospital.HospitalService;
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

    public Page<Review> findByHospitalId(String hospitalId, Pageable pageable) {
        hospitalService.findById(hospitalId);
        return reviewRepository.findByHospital_HospitalId(hospitalId, pageable);
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
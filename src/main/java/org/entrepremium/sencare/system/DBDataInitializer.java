package org.entrepremium.sencare.system;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.DoctorService;
import org.entrepremium.sencare.feature.education.Education;
import org.entrepremium.sencare.feature.education.EducationService;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hosserv.HosServService;
import org.entrepremium.sencare.feature.review.Review;
import org.entrepremium.sencare.feature.review.ReviewService;
import org.entrepremium.sencare.feature.workexperience.WorkExperience;
import org.entrepremium.sencare.feature.workexperience.WorkExperienceService;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.HospitalService;
import org.entrepremium.sencare.feature.specialization.Specialization;
import org.entrepremium.sencare.feature.specialization.SpecializationService;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.UserService;
import org.entrepremium.sencare.system.utils.generators.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DBDataInitializer implements CommandLineRunner {

    private final DoctorService doctorService;
    private final EducationService educationService;
    private final ReviewService reviewService;
    private final WorkExperienceService workExperienceService;
    private final HospitalService hospitalService;
    private final HosServService hosServService;
    private final SpecializationService specializationService;
    private final UserService userService;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {

        // Step 1: Create and save users (if MyUserService is available)
        List<MyUser> savedUsers = new ArrayList<>();
        for (MyUser user : UserGenerator.generateSampleUsers(20)) {
            MyUser savedUser = userService.save(user);
            savedUsers.add(savedUser);
        }

        // Step 2: Create and save specializations FIRST
        System.out.println("Creating specializations...");
        List<Specialization> savedSpecializations = new ArrayList<>();
        for (Specialization specialization : SpecializationGenerator.generateSampleSpecializations()) {
            Specialization savedSpecialization = specializationService.save(specialization);
            savedSpecializations.add(savedSpecialization);
        }

        // Step 3: Create and save hospitals with specializations
        System.out.println("Creating hospitals with specializations...");
        List<Hospital> savedHospitals = new ArrayList<>();
        for (Hospital hospital : HospitalGenerator.generateSampleHospitals(savedUsers, savedSpecializations)) {
            Hospital savedHospital = hospitalService.save(hospital);
            savedHospitals.add(savedHospital);
        }

        // Step 4: Update specializations with hospital relationships (ensure bidirectional consistency)
        System.out.println("Updating specialization-hospital relationships...");
        for (Specialization specialization : savedSpecializations) {
            // The relationship should already be set from the hospital side,
            // but we save again to ensure persistence
            specializationService.save(specialization);
        }


        for (Hospital hospital : savedHospitals) {
            for (HosServ hosServ : HosServGenerator.generateHospitalServices(hospital, random.nextInt(10) + 1)) {
                hosServService.save(hosServ);
            }
        }

        // Step 5: Create and save doctors
        System.out.println("Creating doctors...");
        List<Doctor> savedDoctors = new ArrayList<>();
        for (Doctor doctor : DoctorGenerator.generateSampleDoctors(savedHospitals)) {
            Doctor savedDoctor = doctorService.save(doctor);
            savedDoctors.add(savedDoctor);
        }

        // Step 6: Create and save educations for each doctor
        System.out.println("Creating education records...");
        List<Education> sampleEducations = EducationGenerator.generateSampleEducations(savedDoctors);
        for (Education education : sampleEducations) {
            educationService.save(education);
        }

        // Step 7: Create and save work experiences for each doctor
        System.out.println("Creating work experience records...");
        List<WorkExperience> sampleWorkExperiences = WorkExperienceGenerator.generateSampleWorkExperiences(savedDoctors);
        for (WorkExperience workExperience : sampleWorkExperiences) {
            workExperienceService.save(workExperience);
        }

        List<Review> savedReviews = new ArrayList<>();
        for (Doctor doctor : savedDoctors) {
            for (Review review : ReviewGenerator.generateMultipleDoctorReviews(doctor, savedUsers, doctor.getHospital(), random.nextInt(5) + 1)) {
                Review savedReview = reviewService.save(review);
                savedReviews.add(savedReview);
            }
        }

        System.out.println("Database initialized with:");
        System.out.println("- " + savedUsers.size() + " users");
        // Step 8: Verify relationships
        System.out.println("Verifying hospital-specialization relationships...");
        for (Hospital hospital : savedHospitals) {
            Hospital refreshedHospital = hospitalService.findById(hospital.getHospitalId());
            System.out.println(hospital.getHospitalName() + " has " +
                    refreshedHospital.getSpecializations().size() + " specializations");
        }

        System.out.println("\n=== Database Initialization Complete ===");
        System.out.println("- " + savedUsers.size() + " users");
        System.out.println("- " + savedSpecializations.size() + " specializations");
        System.out.println("- " + savedHospitals.size() + " hospitals");
        System.out.println("- " + savedDoctors.size() + " doctors");
        System.out.println("- " + sampleEducations.size() + " education records");
        System.out.println("- " + savedReviews.size() + " reviews");
        System.out.println("- " + sampleWorkExperiences.size() + " work experience records");
    }
}
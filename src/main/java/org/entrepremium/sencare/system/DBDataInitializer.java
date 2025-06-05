package org.entrepremium.sencare.system;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.DoctorService;
import org.entrepremium.sencare.feature.education.Education;
import org.entrepremium.sencare.feature.education.EducationService;
import org.entrepremium.sencare.feature.workexperience.WorkExperience;
import org.entrepremium.sencare.feature.workexperience.WorkExperienceService;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.HospitalService;
import org.entrepremium.sencare.feature.hospitalspec.HospitalSpec;
import org.entrepremium.sencare.feature.hospitalspec.HospitalSpecService;
import org.entrepremium.sencare.feature.specialization.Specialization;
import org.entrepremium.sencare.feature.specialization.SpecializationService;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.myuser.UserService;
import org.entrepremium.sencare.system.utils.generators.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final DoctorService doctorService;
    private final EducationService educationService;
    private final WorkExperienceService workExperienceService;
    private final HospitalService hospitalService;
    private final SpecializationService specializationService;
    private final HospitalSpecService hospitalSpecService;
    private final UserService myUserService; // Assuming you have this service

    public DBDataInitializer(DoctorService doctorService,
                             EducationService educationService,
                             WorkExperienceService workExperienceService,
                             HospitalService hospitalService,
                             SpecializationService specializationService,
                             HospitalSpecService hospitalSpecService,
                             UserService myUserService) {
        this.doctorService = doctorService;
        this.educationService = educationService;
        this.workExperienceService = workExperienceService;
        this.hospitalService = hospitalService;
        this.specializationService = specializationService;
        this.hospitalSpecService = hospitalSpecService;
        this.myUserService = myUserService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Step 1: Create and save users (if MyUserService is available)
        List<MyUser> users = new ArrayList<>();
        try {
            users = createSampleUsers(); // Create sample users for hospital owners
        } catch (Exception e) {
            System.out.println("MyUser service not available, creating hospitals without user assignments");
        }

        // Step 2: Create and save specializations
        List<Specialization> sampleSpecializations = SpecializationGenerator.generateSampleSpecializations();
        List<Specialization> savedSpecializations = new ArrayList<>();

        for (Specialization specialization : sampleSpecializations) {
            Specialization savedSpecialization = specializationService.save(specialization);
            savedSpecializations.add(savedSpecialization);
        }

        // Step 3: Create and save hospitals
        List<Hospital> sampleHospitals = HospitalGenerator.generateSampleHospitals(users);
        List<Hospital> savedHospitals = new ArrayList<>();

        for (Hospital hospital : sampleHospitals) {
            Hospital savedHospital = hospitalService.save(hospital);
            savedHospitals.add(savedHospital);
        }

        // Step 4: Create and save hospital specializations
        List<HospitalSpec> sampleHospitalSpecs = HospitalSpecGenerator.generateSampleHospitalSpecs(savedHospitals, savedSpecializations);
        for (HospitalSpec hospitalSpec : sampleHospitalSpecs) {
            hospitalSpecService.save(hospitalSpec);
        }

        // Step 5: Create and save doctors
        List<Doctor> sampleDoctors = DoctorGenerator.generateSampleDoctors(savedHospitals);
        List<Doctor> savedDoctors = new ArrayList<>();

        for (Doctor doctor : sampleDoctors) {
            Doctor savedDoctor = doctorService.save(doctor);
            savedDoctors.add(savedDoctor);
        }

        // Step 6: Create and save educations for each doctor
        List<Education> sampleEducations = EducationGenerator.generateSampleEducations(savedDoctors);
        for (Education education : sampleEducations) {
            educationService.save(education);
        }

        // Step 7: Create and save work experiences for each doctor
        List<WorkExperience> sampleWorkExperiences = WorkExperienceGenerator.generateSampleWorkExperiences(savedDoctors);
        for (WorkExperience workExperience : sampleWorkExperiences) {
            workExperienceService.save(workExperience);
        }

        System.out.println("Database initialized with:");
        System.out.println("- " + users.size() + " users");
        System.out.println("- " + savedSpecializations.size() + " specializations");
        System.out.println("- " + savedHospitals.size() + " hospitals");
        System.out.println("- " + sampleHospitalSpecs.size() + " hospital specializations");
        System.out.println("- " + savedDoctors.size() + " doctors");
        System.out.println("- " + sampleEducations.size() + " education records");
        System.out.println("- " + sampleWorkExperiences.size() + " work experience records");
    }

    // Helper method to create sample users if needed
    private List<MyUser> createSampleUsers() {
        List<MyUser> users = new ArrayList<>();

        // Create sample users to be hospital owners/administrators
        String[] userNames = {"John Admin", "Sarah Manager", "Mike Director", "Lisa Owner", "David CEO"};
        String[] userEmails = {"john@hospital.com", "sarah@medical.com", "mike@healthcare.com", "lisa@clinic.com", "david@medcenter.com"};

        for (int i = 0; i < userNames.length; i++) {
            try {
                MyUser user = new MyUser(); // Assuming MyUser has a default constructor
                // Set user properties (adjust these based on your MyUser entity structure)
                // user.setName(userNames[i]);
                // user.setEmail(userEmails[i]);
                // user.setRole("HOSPITAL_ADMIN");
                // Set other required fields
                MyUser savedUser = myUserService.save(user);
                users.add(savedUser);
            } catch (Exception e) {
                System.out.println("Could not create user: " + userNames[i]);
            }
        }

        return users;
    }
}
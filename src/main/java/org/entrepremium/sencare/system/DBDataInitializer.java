package org.entrepremium.sencare.system;

import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.appointment.Appointment;
import org.entrepremium.sencare.feature.appointment.AppointmentService;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.doctor.DoctorService;
import org.entrepremium.sencare.feature.education.Education;
import org.entrepremium.sencare.feature.education.EducationService;
import org.entrepremium.sencare.feature.timeslot.Timeslot;
import org.entrepremium.sencare.feature.timeslot.TimeslotService;
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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DBDataInitializer implements CommandLineRunner {

    private final DoctorService doctorService;
    private final EducationService educationService;
    private final WorkExperienceService workExperienceService;
    private final HospitalService hospitalService;
    private final SpecializationService specializationService;
    private final AppointmentService appointmentService;
    private final TimeslotService timeslotService;
    private final UserService myUserService; // Assuming you have this service

    @Override
    public void run(String... args) throws Exception {
        // Step 1: Create and save users (if MyUserService is available)
        List<MyUser> users = new ArrayList<>();
        try {
            users = createSampleUsers(); // Create sample users for hospital owners
        } catch (Exception e) {
            System.out.println("MyUser service not available, creating hospitals without user assignments");
        }

        // Step 2: Create and save specializations FIRST
        System.out.println("Creating specializations...");
        List<Specialization> sampleSpecializations = SpecializationGenerator.generateSampleSpecializations();
        List<Specialization> savedSpecializations = new ArrayList<>();

        for (Specialization specialization : sampleSpecializations) {
            Specialization savedSpecialization = specializationService.save(specialization);
            savedSpecializations.add(savedSpecialization);
        }

        // Step 3: Create and save hospitals with specializations
        System.out.println("Creating hospitals with specializations...");
        List<Hospital> sampleHospitals = HospitalGenerator.generateSampleHospitals(users, savedSpecializations);
        List<Hospital> savedHospitals = new ArrayList<>();

        for (Hospital hospital : sampleHospitals) {
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

        // Step 5: Create and save doctors
        System.out.println("Creating doctors...");
        List<Doctor> sampleDoctors = DoctorGenerator.generateSampleDoctors(savedHospitals);
        List<Doctor> savedDoctors = new ArrayList<>();

        for (Doctor doctor : sampleDoctors) {
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

        // Step 8: Create and save timeslots for each doctor
        System.out.println("Creating timeslots...");
        List<Timeslot> sampleTimeslots = TimeslotGenerator.generateSampleTimeslots(savedDoctors, savedHospitals.stream()
                .flatMap(h -> h.getHosServs().stream())
                .collect(Collectors.toList()));
        List<Timeslot> savedTimeslots = new ArrayList<>();
        for (Timeslot timeslot : sampleTimeslots) {
            Timeslot savedTimeslot = timeslotService.save(timeslot);
            savedTimeslots.add(savedTimeslot);
        }

       // Step 9: Create and save appointments
        System.out.println("Creating appointments...");
        List<Appointment> sampleAppointments = AppointmentGenerator.generateSampleAppointments(
                savedDoctors, users, savedTimeslots);
        for (Appointment appointment : sampleAppointments) {
            appointmentService.save(appointment);
        }

        // Update the final summary
        System.out.println("\n=== Database Initialization Complete ===");
        System.out.println("- " + users.size() + " users");
        System.out.println("- " + savedSpecializations.size() + " specializations");
        System.out.println("- " + savedHospitals.size() + " hospitals");
        System.out.println("- " + savedDoctors.size() + " doctors");
        System.out.println("- " + sampleEducations.size() + " education records");
        System.out.println("- " + sampleWorkExperiences.size() + " work experience records");
        System.out.println("- " + savedTimeslots.size() + " timeslots");
        System.out.println("- " + sampleAppointments.size() + " appointments");
        System.out.println("==========================================");
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
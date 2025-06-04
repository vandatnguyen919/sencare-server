package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.features.doctor.Doctor;
import org.entrepremium.sencare.features.workexperience.WorkExperience;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkExperienceGenerator {

    private static final String[] HOSPITAL_NAMES = {
            "Mayo Clinic", "Cleveland Clinic", "Johns Hopkins Hospital",
            "Massachusetts General Hospital", "UCLA Medical Center", "NewYork-Presbyterian Hospital",
            "UCSF Medical Center", "Cedars-Sinai Medical Center", "Mount Sinai Hospital",
            "Houston Methodist Hospital", "Northwestern Memorial Hospital", "Duke University Hospital",
            "Vanderbilt University Medical Center", "Barnes-Jewish Hospital", "Tampa General Hospital",
            "Children's Hospital of Philadelphia", "Boston Children's Hospital", "Seattle Children's Hospital",
            "Kaiser Permanente Medical Center", "Scripps Health System", "Adventist Health System",
            "HCA Healthcare", "Tenet Healthcare", "Community Health Systems"
    };

    private static final String[] JOB_TITLES = {
            "Chief of Cardiology", "Senior Attending Physician", "Medical Director",
            "Department Head", "Clinical Associate", "Resident Physician",
            "Fellow", "Staff Physician", "Consulting Physician",
            "Emergency Medicine Physician", "Hospitalist", "Chief Medical Officer",
            "Associate Medical Director", "Clinical Instructor", "Assistant Professor of Medicine",
            "Associate Professor", "Professor of Medicine", "Division Chief",
            "Program Director", "Medical Consultant"
    };

    private static final String[] JOB_DESCRIPTIONS = {
            "Provided comprehensive patient care and supervised medical residents in clinical settings.",
            "Led multidisciplinary team in developing treatment protocols and patient care standards.",
            "Conducted clinical research and published findings in peer-reviewed medical journals.",
            "Managed department operations and implemented quality improvement initiatives.",
            "Delivered specialized medical care and participated in medical education programs.",
            "Coordinated patient care across multiple departments and healthcare disciplines.",
            "Developed and implemented evidence-based treatment guidelines for patient populations.",
            "Supervised medical students and residents in clinical training and patient care.",
            "Collaborated with healthcare teams to optimize patient outcomes and safety measures.",
            "Participated in medical committees and contributed to hospital policy development.",
            "Provided emergency medical care and trauma response in acute care settings.",
            "Conducted medical consultations and second opinions for complex medical cases.",
            "Led quality assurance programs and participated in medical peer review processes.",
            "Managed clinical trials and research studies in specialized medical areas.",
            "Provided telemedicine services and remote patient monitoring capabilities."
    };

    public static List<WorkExperience> generateSampleWorkExperiences(List<Doctor> doctors) {
        List<WorkExperience> workExperiences = new ArrayList<>();
        Random random = new Random();

        for (Doctor doctor : doctors) {
            // Each doctor gets 2-5 work experiences
            int numExperiences = 2 + random.nextInt(4);
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.minusYears(15 + random.nextInt(10)); // Start 15-25 years ago

            for (int i = 0; i < numExperiences; i++) {
                WorkExperience workExp = new WorkExperience();

                workExp.setHospitalName(HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)]);
                workExp.setJobTitle(JOB_TITLES[random.nextInt(JOB_TITLES.length)]);
                workExp.setDescription(JOB_DESCRIPTIONS[random.nextInt(JOB_DESCRIPTIONS.length)]);

                workExp.setStartDate(startDate);

                // Generate end date (2-6 years later), unless it's the most recent position
                if (i < numExperiences - 1) {
                    LocalDate endDate = startDate.plusYears(2 + random.nextInt(5));
                    workExp.setEndDate(endDate);
                    startDate = endDate.plusMonths(1 + random.nextInt(6)); // Gap between jobs
                } else {
                    // Current position - no end date or recent end date
                    if (random.nextBoolean()) {
                        workExp.setEndDate(null); // Current position
                    } else {
                        workExp.setEndDate(currentDate.minusMonths(random.nextInt(12))); // Recent position
                    }
                }

                workExp.setDoctor(doctor);
                workExperiences.add(workExp);
            }
        }

        return workExperiences;
    }

    public static WorkExperience createWorkExperience(Doctor doctor, String hospitalName,
                                                      String jobTitle, LocalDate startDate, LocalDate endDate) {
        Random random = new Random();
        WorkExperience workExp = new WorkExperience();

        workExp.setHospitalName(hospitalName);
        workExp.setJobTitle(jobTitle);
        workExp.setDescription(JOB_DESCRIPTIONS[random.nextInt(JOB_DESCRIPTIONS.length)]);
        workExp.setStartDate(startDate);
        workExp.setEndDate(endDate);
        workExp.setDoctor(doctor);

        return workExp;
    }

    public static List<WorkExperience> generateWorkExperiencesForDoctor(Doctor doctor) {
        List<WorkExperience> experiences = new ArrayList<>();
        Random random = new Random();
        LocalDate currentDate = LocalDate.now();

        // First job (junior position)
        LocalDate firstJobStart = currentDate.minusYears(8 + random.nextInt(5));
        WorkExperience firstJob = createWorkExperience(
                doctor,
                HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)],
                "Resident Physician",
                firstJobStart,
                firstJobStart.plusYears(2 + random.nextInt(2))
        );
        experiences.add(firstJob);

        // Second job (mid-level position)
        LocalDate secondJobStart = firstJob.getEndDate().plusMonths(1 + random.nextInt(3));
        WorkExperience secondJob = createWorkExperience(
                doctor,
                HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)],
                "Staff Physician",
                secondJobStart,
                secondJobStart.plusYears(3 + random.nextInt(3))
        );
        experiences.add(secondJob);

        // Current job (senior position)
        LocalDate currentJobStart = secondJob.getEndDate().plusMonths(1 + random.nextInt(6));
        WorkExperience currentJob = createWorkExperience(
                doctor,
                HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)],
                "Senior Attending Physician",
                currentJobStart,
                null // Current position
        );
        experiences.add(currentJob);

        return experiences;
    }
}
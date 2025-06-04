package org.entrepremium.sencare.system.utils.generators;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.education.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EducationGenerator {

    private static final String[] MEDICAL_COLLEGES = {
            "Harvard Medical School", "Johns Hopkins University School of Medicine",
            "Stanford University School of Medicine", "University of Pennsylvania Perelman School of Medicine",
            "Washington University School of Medicine", "Columbia University Vagelos College of Physicians and Surgeons",
            "Duke University School of Medicine", "University of California, San Francisco School of Medicine",
            "Mayo Clinic Alix School of Medicine", "Vanderbilt University School of Medicine",
            "University of Michigan Medical School", "Yale School of Medicine",
            "Northwestern University Feinberg School of Medicine", "University of Chicago Pritzker School of Medicine",
            "Mount Sinai Icahn School of Medicine", "University of Pittsburgh School of Medicine",
            "Boston University School of Medicine", "Georgetown University School of Medicine",
            "Emory University School of Medicine", "University of Southern California Keck School of Medicine"
    };

    private static final String[] DEGREE_TYPES = {
            "Doctor of Medicine (M.D.)",
            "Bachelor of Medicine, Bachelor of Surgery (MBBS)",
            "Doctor of Osteopathic Medicine (D.O.)",
            "Master of Public Health (MPH)",
            "Master of Science in Medicine",
            "PhD in Medical Sciences",
            "Fellowship in Cardiology",
            "Fellowship in Neurology",
            "Residency in Internal Medicine",
            "Specialty Certificate"
    };

    private static final String[] EDUCATION_DESCRIPTIONS = {
            "Comprehensive medical education with focus on clinical excellence and patient care.",
            "Advanced training in diagnostic procedures and therapeutic interventions.",
            "Specialized coursework in medical research and evidence-based practice.",
            "Intensive clinical training with emphasis on interdisciplinary collaboration.",
            "Research-focused program with publications in peer-reviewed medical journals.",
            "Hands-on clinical experience in diverse healthcare settings.",
            "Leadership training in healthcare administration and medical ethics.",
            "Advanced fellowship training in specialized medical procedures.",
            "Residency program with extensive surgical and clinical exposure.",
            "Continuing medical education with board certification maintenance."
    };

    private static final String[] ISSUING_ORGANIZATIONS = {
            "American Board of Medical Specialties",
            "Royal College of Physicians",
            "American Medical Association",
            "International Association of Medical Colleges",
            "World Health Organization",
            "American College of Physicians",
            "American Board of Internal Medicine",
            "National Board of Medical Examiners",
            "Educational Commission for Foreign Medical Graduates",
            "Accreditation Council for Graduate Medical Education"
    };

    public static List<Education> generateSampleEducations(List<Doctor> doctors) {
        List<Education> educations = new ArrayList<>();
        Random random = new Random();

        for (Doctor doctor : doctors) {
            // Each doctor gets 2-4 education records
            int numEducations = 2 + random.nextInt(3);

            for (int i = 0; i < numEducations; i++) {
                Education education = new Education();

                education.setCollegeName(MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)]);
                education.setDescription(EDUCATION_DESCRIPTIONS[random.nextInt(EDUCATION_DESCRIPTIONS.length)]);
                education.setIssuedBy(ISSUING_ORGANIZATIONS[random.nextInt(ISSUING_ORGANIZATIONS.length)]);

                // Generate year between 1990-2023, with more recent years for younger doctors
                int baseYear = 1990 + random.nextInt(34);
                education.setIssuedYear(baseYear + i * 2); // Stagger education years

                education.setDoctor(doctor);
                educations.add(education);
            }
        }

        return educations;
    }

    public static Education createEducation(Doctor doctor, String collegeName, String degreeType, Integer year) {
        Random random = new Random();
        Education education = new Education();

        education.setCollegeName(collegeName);
        education.setDescription(degreeType + " - " + EDUCATION_DESCRIPTIONS[random.nextInt(EDUCATION_DESCRIPTIONS.length)]);
        education.setIssuedBy(ISSUING_ORGANIZATIONS[random.nextInt(ISSUING_ORGANIZATIONS.length)]);
        education.setIssuedYear(year);
        education.setDoctor(doctor);

        return education;
    }

    public static List<Education> generateEducationsForDoctor(Doctor doctor) {
        List<Education> educations = new ArrayList<>();
        Random random = new Random();

        // Medical degree (oldest)
        Education medicalDegree = createEducation(
                doctor,
                MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)],
                "Doctor of Medicine (M.D.)",
                2000 + random.nextInt(15)
        );
        educations.add(medicalDegree);

        // Residency
        Education residency = createEducation(
                doctor,
                MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)],
                "Residency Program",
                medicalDegree.getIssuedYear() + 1 + random.nextInt(3)
        );
        educations.add(residency);

        // Possible fellowship
        if (random.nextBoolean()) {
            Education fellowship = createEducation(
                    doctor,
                    MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)],
                    "Fellowship Program",
                    residency.getIssuedYear() + 1 + random.nextInt(2)
            );
            educations.add(fellowship);
        }

        return educations;
    }
}
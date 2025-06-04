package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.features.doctor.Doctor;
import org.entrepremium.sencare.features.hospital.Hospital;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoctorGenerator {

    private static final String[] DOCTOR_NAMES = {
            "Dr. Sarah Johnson", "Dr. Michael Chen", "Dr. Emily Rodriguez", "Dr. David Kim",
            "Dr. Lisa Thompson", "Dr. James Wilson", "Dr. Maria Garcia", "Dr. Robert Taylor",
            "Dr. Jennifer Lee", "Dr. Christopher Brown", "Dr. Amanda Davis", "Dr. Kevin Martinez",
            "Dr. Rachel Green", "Dr. Daniel White", "Dr. Nicole Adams", "Dr. Matthew Clark",
            "Dr. Jessica Lewis", "Dr. Andrew Hall", "Dr. Stephanie Young", "Dr. Brian Miller"
    };

    private static final String[] SPECIALTIES = {
            "Cardiologist", "Neurologist", "Pediatrician", "Orthopedic Surgeon",
            "Dermatologist", "Psychiatrist", "Oncologist", "Gastroenterologist",
            "Pulmonologist", "Endocrinologist", "Rheumatologist", "Ophthalmologist",
            "Otolaryngologist", "Urologist", "Radiologist", "Anesthesiologist",
            "Emergency Medicine", "Family Medicine", "Internal Medicine", "Gynecologist"
    };

    private static final String[] DESCRIPTIONS = {
            "Experienced physician with a passion for patient care and medical excellence.",
            "Dedicated doctor committed to providing comprehensive healthcare services.",
            "Board-certified specialist with extensive experience in diagnostic and treatment procedures.",
            "Compassionate healthcare provider focused on personalized patient treatment plans.",
            "Medical expert with a strong background in research and clinical practice.",
            "Skilled physician known for innovative treatment approaches and patient advocacy.",
            "Healthcare professional with a commitment to continuous learning and improvement.",
            "Experienced practitioner specializing in evidence-based medical care.",
            "Patient-centered doctor with expertise in preventive and therapeutic medicine.",
            "Medical specialist dedicated to advancing healthcare through quality patient care."
    };

    private static final String[] AVATAR_URLS = {
            "https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400",
            "https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400",
            "https://images.unsplash.com/photo-1582750433449-648ed127bb54?w=400",
            "https://images.unsplash.com/photo-1613928162506-291b7b2cc552?w=400",
            "https://images.unsplash.com/photo-1594824388975-d4822fcbe8ba?w=400",
            "https://images.unsplash.com/photo-1607990281513-2c110a25bd8c?w=400",
            "https://images.unsplash.com/photo-1584467735815-f778f274e296?w=400",
            "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400"
    };

    public static List<Doctor> generateSampleDoctors(List<Hospital> hospitals) {
        List<Doctor> doctors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < DOCTOR_NAMES.length; i++) {
            Doctor doctor = new Doctor();

            doctor.setDoctorName(DOCTOR_NAMES[i]);
            doctor.setDoctorAvatar(AVATAR_URLS[random.nextInt(AVATAR_URLS.length)]);

            // Create description with specialty
            String specialty = SPECIALTIES[i % SPECIALTIES.length];
            doctor.setDoctorDescription(specialty + " - " + DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);

            // Generate price between $50 - $500
            BigDecimal price = BigDecimal.valueOf(50 + random.nextInt(451));
            doctor.setDoctorPrice(price);

            // Assign to random hospital
            if (!hospitals.isEmpty()) {
                Hospital hospital = hospitals.get(random.nextInt(hospitals.size()));
                doctor.setHospital(hospital);
            }

            doctors.add(doctor);
        }

        return doctors;
    }

    public static Doctor createSampleDoctor(String name, String specialty, Hospital hospital) {
        Random random = new Random();
        Doctor doctor = new Doctor();

        doctor.setDoctorName(name);
        doctor.setDoctorAvatar(AVATAR_URLS[random.nextInt(AVATAR_URLS.length)]);
        doctor.setDoctorDescription(specialty + " - " + DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);
        doctor.setDoctorPrice(BigDecimal.valueOf(100 + random.nextInt(300)));
        doctor.setHospital(hospital);

        return doctor;
    }
}
package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.features.hospital.Hospital;
import org.entrepremium.sencare.features.myuser.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HospitalGenerator {

    private static final String[] HOSPITAL_NAMES = {
            "City General Hospital",
            "Metropolitan Medical Center",
            "University Hospital",
            "Regional Health Center",
            "Community Medical Hospital",
            "St. Mary's Hospital",
            "Central Hospital",
            "Riverside Medical Center",
            "Mercy General Hospital",
            "Presbyterian Hospital",
            "Sacred Heart Medical Center",
            "Good Samaritan Hospital",
            "Memorial Hospital",
            "Northside Hospital",
            "Southside Medical Center",
            "Eastside Regional Hospital",
            "Westside Community Hospital",
            "Children's Medical Center",
            "Women's Health Hospital",
            "Cardiac Care Center"
    };

    private static final String[] HOSPITAL_DESCRIPTIONS = {
            "A leading healthcare institution committed to providing exceptional medical care and innovative treatments.",
            "State-of-the-art medical facility offering comprehensive healthcare services to the community.",
            "Multi-specialty hospital with advanced technology and compassionate patient care.",
            "Teaching hospital affiliated with medical school, providing cutting-edge medical education and research.",
            "Community-focused hospital dedicated to improving health outcomes for all patients.",
            "Specialized medical center with expertise in complex medical conditions and procedures.",
            "Full-service hospital offering emergency care, surgery, and specialized medical services.",
            "Regional medical center serving diverse populations with culturally competent care.",
            "Non-profit hospital committed to accessible healthcare and community wellness programs.",
            "Academic medical center combining clinical excellence with medical research and education.",
            "Patient-centered hospital focused on quality care and positive health outcomes.",
            "Modern healthcare facility equipped with latest medical technology and expert staff.",
            "Comprehensive medical center offering preventive care, diagnosis, and treatment services.",
            "Specialty hospital dedicated to specific medical conditions with expert physicians.",
            "Community hospital providing personalized care and building lasting patient relationships."
    };

    private static final String[] HOSPITAL_AVATARS = {
            "https://images.unsplash.com/photo-1586773860418-d37222d8fce3?w=400",
            "https://images.unsplash.com/photo-1551190822-a9333d879b1f?w=400",
            "https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400",
            "https://images.unsplash.com/photo-1579684385127-1ef15d508118?w=400",
            "https://images.unsplash.com/photo-1576091160399-112ba8d25d1f?w=400",
            "https://images.unsplash.com/photo-1582560469781-1e985f8da4d6?w=400",
            "https://images.unsplash.com/photo-1581594549595-35f6edc7b762?w=400",
            "https://images.unsplash.com/photo-1504813184591-01572f98c85f?w=400"
    };

    private static final String[] PHONE_PREFIXES = {
            "+1-555-", "+1-444-", "+1-333-", "+1-222-", "+1-111-"
    };

    public static List<Hospital> generateSampleHospitals(List<MyUser> users) {
        List<Hospital> hospitals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < HOSPITAL_NAMES.length && i < 15; i++) {
            Hospital hospital = new Hospital();

            hospital.setHospitalName(HOSPITAL_NAMES[i]);
            hospital.setHospitalDescription(HOSPITAL_DESCRIPTIONS[random.nextInt(HOSPITAL_DESCRIPTIONS.length)]);
            hospital.setHospitalAvatar(HOSPITAL_AVATARS[random.nextInt(HOSPITAL_AVATARS.length)]);

            // Generate phone number
            String phonePrefix = PHONE_PREFIXES[random.nextInt(PHONE_PREFIXES.length)];
            String phoneNumber = phonePrefix + String.format("%03d-%04d",
                    100 + random.nextInt(900),
                    1000 + random.nextInt(9000));
            hospital.setHospitalPhone(phoneNumber);

            // Assign to random user (hospital owner/admin)
            if (!users.isEmpty()) {
                MyUser user = users.get(random.nextInt(users.size()));
                hospital.setUser(user);
            }

            hospitals.add(hospital);
        }

        return hospitals;
    }

    public static Hospital createSampleHospital(String name, String description, MyUser user) {
        Random random = new Random();
        Hospital hospital = new Hospital();

        hospital.setHospitalName(name);
        hospital.setHospitalDescription(description);
        hospital.setHospitalAvatar(HOSPITAL_AVATARS[random.nextInt(HOSPITAL_AVATARS.length)]);

        String phonePrefix = PHONE_PREFIXES[random.nextInt(PHONE_PREFIXES.length)];
        String phoneNumber = phonePrefix + String.format("%03d-%04d",
                100 + random.nextInt(900),
                1000 + random.nextInt(9000));
        hospital.setHospitalPhone(phoneNumber);

        hospital.setUser(user);

        return hospital;
    }

    public static List<Hospital> generateHospitalsWithoutUsers(int count) {
        List<Hospital> hospitals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count && i < HOSPITAL_NAMES.length; i++) {
            Hospital hospital = new Hospital();

            hospital.setHospitalName(HOSPITAL_NAMES[i]);
            hospital.setHospitalDescription(HOSPITAL_DESCRIPTIONS[random.nextInt(HOSPITAL_DESCRIPTIONS.length)]);
            hospital.setHospitalAvatar(HOSPITAL_AVATARS[random.nextInt(HOSPITAL_AVATARS.length)]);

            String phonePrefix = PHONE_PREFIXES[random.nextInt(PHONE_PREFIXES.length)];
            String phoneNumber = phonePrefix + String.format("%03d-%04d",
                    100 + random.nextInt(900),
                    1000 + random.nextInt(9000));
            hospital.setHospitalPhone(phoneNumber);

            hospitals.add(hospital);
        }

        return hospitals;
    }
}
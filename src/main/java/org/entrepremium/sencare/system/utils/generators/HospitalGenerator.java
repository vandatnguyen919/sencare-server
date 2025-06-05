package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.myuser.MyUser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HospitalGenerator {

    private static final String[] HOSPITAL_NAMES = {
            "Bệnh viện Đa khoa Thành phố",
            "Trung tâm Y tế Đại học",
            "Bệnh viện Chợ Rẫy",
            "Bệnh viện Bạch Mai",
            "Bệnh viện Việt Đức",
            "Bệnh viện K",
            "Bệnh viện Nhi Trung ương",
            "Bệnh viện Tim Hà Nội",
            "Bệnh viện Phụ sản Hà Nội",
            "Bệnh viện Hữu nghị Việt Đức",
            "Bệnh viện 108",
            "Bệnh viện Quân y 103",
            "Bệnh viện Đại học Y Hà Nội",
            "Bệnh viện Răng Hàm Mặt",
            "Bệnh viện Mắt Trung ương",
            "Bệnh viện Da liễu Trung ương",
            "Bệnh viện Nội tiết Trung ương",
            "Bệnh viện Tâm thần Hà Nội",
            "Bệnh viện Ung bướu Hà Nội",
            "Bệnh viện Nhiệt đới Trung ương"
    };

    private static final String[] HOSPITAL_DESCRIPTIONS = {
            "Bệnh viện đa khoa hàng đầu với đội ngũ y bác sĩ giàu kinh nghiệm và trang thiết bị hiện đại.",
            "Trung tâm y tế uy tín chuyên điều trị các bệnh lý phức tạp với công nghệ tiên tiến.",
            "Bệnh viện công lập với truyền thống lâu đời, phục vụ tận tình cho người bệnh.",
            "Cơ sở y tế chất lượng cao với các chuyên khoa đầy đủ và dịch vụ chăm sóc toàn diện.",
            "Bệnh viện chuyên khoa với đội ngũ chuyên gia đầu ngành và kỹ thuật điều trị tiên tiến.",
            "Trung tâm y tế hiện đại cam kết mang đến dịch vụ chăm sóc sức khỏe tối ưu.",
            "Bệnh viện đa tuyến với nhiều năm kinh nghiệm trong việc chăm sóc và điều trị bệnh nhân.",
            "Cơ sở y tế uy tín với phương châm 'Bệnh nhân là trung tâm' và chất lượng dịch vụ cao.",
            "Bệnh viện chuyên nghiệp với hệ thống trang thiết bị y tế đồng bộ và hiện đại.",
            "Trung tâm y tế hàng đầu trong khu vực với đội ngũ y bác sĩ tâm huyết và giàu kinh nghiệm.",
            "Bệnh viện đa khoa với nhiều chuyên khoa mạnh và dịch vụ chăm sóc sức khỏe chất lượng.",
            "Cơ sở y tế hiện đại áp dụng các kỹ thuật điều trị tiên tiến và an toàn.",
            "Bệnh viện uy tín với truyền thống phục vụ nhân dân và không ngừng đổi mới.",
            "Trung tâm y tế chuyên nghiệp với môi trường chăm sóc thân thiện và chu đáo.",
            "Bệnh viện chất lượng cao với sứ mệnh bảo vệ và nâng cao sức khỏe cộng đồng."
    };

    // Using more reliable image sources
    private static final String[] HOSPITAL_AVATARS = {
            "https://images.unsplash.com/photo-1586773860418-d37222d8fce3?w=400",
            "https://images.unsplash.com/photo-1551190822-a9333d879b1f?w=400",
            "https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400",
            "https://images.unsplash.com/photo-1579684385127-1ef15d508118?w=400",
            "https://images.unsplash.com/photo-1581594549595-35f6edc7b762?w=400",
            "https://images.unsplash.com/photo-1504813184591-01572f98c85f?w=400"
    };

    private static final String[] PHONE_PREFIXES = {
            "+84-24-", "+84-28-", "+84-236-", "+84-256-", "+84-274-"
    };

    private static boolean isUrlAvailable(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }

    private static String getValidAvatarUrl() {
        Random random = new Random();
        List<String> availableUrls = new ArrayList<>();

        for (String url : HOSPITAL_AVATARS) {
            if (isUrlAvailable(url)) {
                availableUrls.add(url);
            }
        }

        if (availableUrls.isEmpty()) {
            // Fallback to placeholder if none are available
            return "https://via.placeholder.com/400x300/4A90E2/FFFFFF?text=Hospital";
        }

        return availableUrls.get(random.nextInt(availableUrls.size()));
    }

    public static List<Hospital> generateSampleHospitals(List<MyUser> users) {
        List<Hospital> hospitals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < HOSPITAL_NAMES.length && i < 15; i++) {
            Hospital hospital = new Hospital();

            hospital.setHospitalName(HOSPITAL_NAMES[i]);
            hospital.setHospitalDescription(HOSPITAL_DESCRIPTIONS[random.nextInt(HOSPITAL_DESCRIPTIONS.length)]);
            hospital.setHospitalAvatar(getValidAvatarUrl());

            // Generate Vietnamese phone number
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
        Hospital hospital = new Hospital();

        hospital.setHospitalName(name);
        hospital.setHospitalDescription(description);
        hospital.setHospitalAvatar(getValidAvatarUrl());

        Random random = new Random();
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
            hospital.setHospitalAvatar(getValidAvatarUrl());

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
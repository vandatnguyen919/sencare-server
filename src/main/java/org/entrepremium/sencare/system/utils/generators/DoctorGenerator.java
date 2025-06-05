package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hospital.Hospital;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoctorGenerator {

    private static final String[] DOCTOR_NAMES = {
            "BS. Nguyễn Văn Hùng", "BS. Trần Thị Lan", "BS. Lê Minh Đức", "BS. Phạm Thị Hoa",
            "BS. Hoàng Văn Nam", "BS. Đỗ Thị Mai", "BS. Vũ Minh Tuấn", "BS. Bùi Thị Linh",
            "BS. Đinh Văn Khoa", "BS. Phan Thị Ngọc", "BS. Lý Minh Hải", "BS. Tô Thị Bích",
            "PGS.TS. Nguyễn Thanh Long", "GS.TS. Trần Văn Thuận", "BS.CKI Lê Thị Hương",
            "ThS.BS. Phạm Minh Đức", "BS.CKII Hoàng Thị Nga", "PGS. Vũ Văn Thành",
            "BS. Đặng Thị Tuyết", "BS. Nông Văn Dũng"
    };

    private static final String[] SPECIALTIES = {
            "Bác sĩ Tim mạch", "Bác sĩ Thần kinh", "Bác sĩ Nhi khoa", "Bác sĩ Chỉnh hình",
            "Bác sĩ Da liễu", "Bác sĩ Tâm thần", "Bác sĩ Ung bướu", "Bác sĩ Tiêu hóa",
            "Bác sĩ Hô hấp", "Bác sĩ Nội tiết", "Bác sĩ Thấp khớp", "Bác sĩ Mắt",
            "Bác sĩ Tai mũi họng", "Bác sĩ Tiết niệu", "Bác sĩ Chẩn đoán hình ảnh",
            "Bác sĩ Gây mê hồi sức", "Bác sĩ Cấp cứu", "Bác sĩ Đa khoa", "Bác sĩ Nội khoa",
            "Bác sĩ Phụ sản"
    };

    private static final String[] DESCRIPTIONS = {
            "Bác sĩ giàu kinh nghiệm với niềm đam mê chăm sóc bệnh nhân và y học xuất sắc.",
            "Thầy thuốc tận tâm cam kết cung cấp dịch vụ chăm sóc sức khỏe toàn diện.",
            "Chuyên gia được chứng nhận với kinh nghiệm phong phú trong chẩn đoán và điều trị.",
            "Nhà cung cấp dịch vụ y tế tận tình tập trung vào kế hoạch điều trị cá nhân hóa.",
            "Chuyên gia y khoa với nền tảng vững chắc về nghiên cứu và thực hành lâm sàng.",
            "Bác sĩ có kỹ năng cao được biết đến với phương pháp điều trị sáng tạo.",
            "Chuyên gia y tế với cam kết học tập và cải tiến liên tục.",
            "Bác sĩ giàu kinh nghiệm chuyên về chăm sóc y học dựa trên bằng chứng.",
            "Thầy thuốc lấy bệnh nhân làm trung tâm với chuyên môn về y học dự phòng và điều trị.",
            "Chuyên gia y khoa tận tâm thúc đẩy chăm sóc sức khỏe thông qua chất lượng bệnh nhân."
    };

    // Using placeholder images for reliability
    private static final String[] AVATAR_URLS = {
            "https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400",
            "https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400",
            "https://images.unsplash.com/photo-1594824388975-d4822fcbe8ba?w=400",
            "https://images.unsplash.com/photo-1607990281513-2c110a25bd8c?w=400",
            "https://images.unsplash.com/photo-1584467735815-f778f274e296?w=400",
            "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400"
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

        for (String url : AVATAR_URLS) {
            if (isUrlAvailable(url)) {
                availableUrls.add(url);
            }
        }

        if (availableUrls.isEmpty()) {
            // Fallback to placeholder if none are available
            return "https://via.placeholder.com/400x400/4A90E2/FFFFFF?text=Doctor";
        }

        return availableUrls.get(random.nextInt(availableUrls.size()));
    }

    public static List<Doctor> generateSampleDoctors(List<Hospital> hospitals) {
        List<Doctor> doctors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < DOCTOR_NAMES.length; i++) {
            Doctor doctor = new Doctor();

            doctor.setDoctorName(DOCTOR_NAMES[i]);
            doctor.setDoctorAvatar(getValidAvatarUrl());

            // Create description with specialty
            String specialty = SPECIALTIES[i % SPECIALTIES.length];
            doctor.setDoctorDescription(specialty + " - " + DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);

            // Generate price between 200,000 - 2,000,000 VND (roughly $8-80 USD)
            BigDecimal price = BigDecimal.valueOf(200000 + random.nextInt(1800001));
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
        doctor.setDoctorAvatar(getValidAvatarUrl());
        doctor.setDoctorDescription(specialty + " - " + DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);
        doctor.setDoctorPrice(BigDecimal.valueOf(500000 + random.nextInt(1500000))); // 500k-2M VND
        doctor.setHospital(hospital);

        return doctor;
    }
}
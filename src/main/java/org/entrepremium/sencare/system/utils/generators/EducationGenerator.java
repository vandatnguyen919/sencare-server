package org.entrepremium.sencare.system.utils.generators;
import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.education.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EducationGenerator {

    private static final String[] MEDICAL_COLLEGES = {
            "Đại học Y Hà Nội", "Đại học Y dược TP.HCM",
            "Đại học Y Huế", "Đại học Y Thái Bình",
            "Đại học Y khoa Phạm Ngọc Thạch", "Đại học Y Dược Cần Thơ",
            "Đại học Y Dược Hải Phòng", "Đại học Y Dược Thái Nguyên",
            "Học viện Quân y", "Đại học Y Dược Buôn Ma Thuột",
            "Đại học Kỹ thuật Y tế Hải Dương", "Đại học Y Dương Nội",
            "Trường Đại học Y Dược - Đại học Quốc gia Hà Nội", "Đại học Y Dược Vinh",
            "Đại học Y tế Công cộng", "Trường Cao đẳng Y tế Hà Nội",
            "Đại học Duy Tân - Khoa Y", "Đại học Nguyễn Tất Thành - Khoa Y",
            "Đại học Đông Á - Khoa Y Dược", "Đại học Gia Định - Khoa Y"
    };

    private static final String[] DEGREE_TYPES = {
            "Bác sĩ Đa khoa",
            "Bác sĩ Y học Cổ truyền",
            "Dược sĩ Đại học",
            "Thạc sĩ Y học",
            "Tiến sĩ Y học",
            "Chuyên khoa I - Tim mạch",
            "Chuyên khoa I - Thần kinh",
            "Chuyên khoa I - Nội khoa",
            "Chuyên khoa II - Phẫu thuật",
            "Bằng chuyên môn Y tế",
            "Thạc sĩ Y tế Công cộng",
            "Chuyên khoa I - Sản phụ khoa",
            "Chuyên khoa I - Nhi khoa",
            "Chuyên khoa I - Mắt"
    };

    private static final String[] EDUCATION_DESCRIPTIONS = {
            "Đào tạo y khoa toàn diện với trọng tâm về thực hành lâm sàng và chăm sóc bệnh nhân.",
            "Đào tạo nâng cao về các thủ thuật chẩn đoán và can thiệp điều trị.",
            "Chương trình học chuyên sâu về nghiên cứu y học và thực hành dựa trên bằng chứng.",
            "Đào tạo lâm sàng chuyên sâu với trọng tâm về hợp tác liên ngành.",
            "Chương trình nghiên cứu với các công trình được xuất bản trên tạp chí y học uy tín.",
            "Kinh nghiệm lâm sàng thực tế trong các cơ sở y tế đa dạng.",
            "Đào tạo lãnh đạo về quản lý y tế và đạo đức y học.",
            "Đào tạo chuyên khoa nâng cao về các thủ thuật y học chuyên biệt.",
            "Chương trình nội trú với kinh nghiệm phẫu thuật và lâm sàng phong phú.",
            "Giáo dục y học liên tục với duy trì chứng chỉ hành nghề."
    };

    private static final String[] ISSUING_ORGANIZATIONS = {
            "Bộ Y tế Việt Nam",
            "Hội Thầy thuốc Việt Nam",
            "Hội Y học Việt Nam",
            "Trung ương Hội Thầy thuốc trẻ Việt Nam",
            "Hội Tim mạch học Việt Nam",
            "Hội Nhi khoa Việt Nam",
            "Hội Sản phụ khoa Việt Nam",
            "Hội Thần kinh học Việt Nam",
            "Hội Y học Cổ truyền Việt Nam",
            "Cục Quản lý Khám chữa bệnh - Bộ Y tế",
            "Hội Nội khoa Việt Nam",
            "Hội Phẫu thuật Việt Nam",
            "Trường Đại học Y Hà Nội",
            "Đại học Y dược TP.HCM"
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
                "Bác sĩ Đa khoa",
                2000 + random.nextInt(15)
        );
        educations.add(medicalDegree);

        // Residency/Specialization
        Education residency = createEducation(
                doctor,
                MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)],
                "Chương trình Nội trú",
                medicalDegree.getIssuedYear() + 1 + random.nextInt(3)
        );
        educations.add(residency);

        // Possible fellowship/advanced specialization
        if (random.nextBoolean()) {
            Education fellowship = createEducation(
                    doctor,
                    MEDICAL_COLLEGES[random.nextInt(MEDICAL_COLLEGES.length)],
                    "Chuyên khoa I",
                    residency.getIssuedYear() + 1 + random.nextInt(2)
            );
            educations.add(fellowship);
        }

        return educations;
    }
}
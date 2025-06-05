package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.features.doctor.Doctor;
import org.entrepremium.sencare.features.workexperience.WorkExperience;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkExperienceGenerator {

    private static final String[] HOSPITAL_NAMES = {
            "Bệnh viện Bạch Mai", "Bệnh viện Chợ Rẫy", "Bệnh viện Việt Đức",
            "Bệnh viện K", "Bệnh viện Đại học Y Hà Nội", "Bệnh viện 108",
            "Bệnh viện Nhân dân 115", "Bệnh viện Thống Nhất", "Bệnh viện Từ Dũ",
            "Bệnh viện Nhi đồng 1", "Bệnh viện Nhi đồng 2", "Bệnh viện Mắt TP.HCM",
            "Bệnh viện Tim Hà Nội", "Bệnh viện Ung bướu TP.HCM", "Bệnh viện E",
            "Bệnh viện Đại học Y dược TP.HCM", "Bệnh viện Hữu nghị Việt Đức",
            "Bệnh viện Saint Paul", "Bệnh viện FV", "Bệnh viện Vinmec",
            "Bệnh viện Đa khoa Medlatec", "Bệnh viện Đa khoa Hồng Ngọc",
            "Bệnh viện Columbia Asia", "Bệnh viện Đa khoa Tâm Anh",
            "Bệnh viện Đa khoa Quốc tế Thu Cúc", "Bệnh viện Y học cổ truyền Trung ương",
            "Bệnh viện Quân y 103", "Bệnh viện Trung ương Huế", "Bệnh viện Đà Nẵng",
            "Bệnh viện Đa khoa tỉnh An Giang", "Bệnh viện Đa khoa Đồng Nai"
    };

    private static final String[] JOB_TITLES = {
            "Trưởng khoa Tim mạch", "Bác sĩ điều trị", "Giám đốc Y khoa",
            "Trưởng khoa", "Bác sĩ lâm sàng", "Bác sĩ nội trú",
            "Bác sĩ chuyên khoa", "Bác sĩ chuyên môn", "Bác sĩ tư vấn",
            "Bác sĩ cấp cứu", "Bác sĩ nội khoa", "Giám đốc Bệnh viện",
            "Phó Giám đốc Y khoa", "Giảng viên lâm sàng", "Trợ lý Giáo sư Y khoa",
            "Phó Giáo sư", "Giáo sư Y khoa", "Trưởng bộ môn",
            "Giám đốc Chương trình", "Bác sĩ cố vấn", "Phó Trưởng khoa",
            "Bác sĩ trưởng", "Bác sĩ chính", "Bác sĩ cao cấp"
    };

    private static final String[] JOB_DESCRIPTIONS = {
            "Cung cấp dịch vụ chăm sóc bệnh nhân toàn diện và giám sát các bác sĩ nội trú trong môi trường lâm sàng.",
            "Lãnh đạo nhóm đa chuyên khoa trong việc phát triển quy trình điều trị và tiêu chuẩn chăm sóc bệnh nhân.",
            "Tiến hành nghiên cứu lâm sàng và công bố kết quả trên các tạp chí y học có uy tín.",
            "Quản lý hoạt động khoa và thực hiện các sáng kiến cải thiện chất lượng.",
            "Cung cấp dịch vụ y tế chuyên khoa và tham gia các chương trình giáo dục y học.",
            "Phối hợp chăm sóc bệnh nhân giữa nhiều khoa và chuyên ngành y tế.",
            "Phát triển và thực hiện hướng dẫn điều trị dựa trên bằng chứng cho các nhóm bệnh nhân.",
            "Giám sát sinh viên y khoa và bác sĩ nội trú trong đào tạo lâm sàng và chăm sóc bệnh nhân.",
            "Hợp tác với các nhóm y tế để tối ưu hóa kết quả điều trị và các biện pháp an toàn bệnh nhân.",
            "Tham gia các ủy ban y khoa và đóng góp vào việc phát triển chính sách bệnh viện.",
            "Cung cấp dịch vụ y tế cấp cứu và ứng phó chấn thương trong môi trường chăm sóc cấp tính.",
            "Tiến hành tư vấn y khoa và ý kiến thứ hai cho các ca bệnh phức tạp.",
            "Dẫn dắt các chương trình đảm bảo chất lượng và tham gia quá trình đánh giá chuyên môn y khoa.",
            "Quản lý các thử nghiệm lâm sàng và nghiên cứu trong các lĩnh vực y học chuyên biệt.",
            "Cung cấp dịch vụ y tế từ xa và khả năng theo dõi bệnh nhân từ xa."
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
                "Bác sĩ nội trú",
                firstJobStart,
                firstJobStart.plusYears(2 + random.nextInt(2))
        );
        experiences.add(firstJob);

        // Second job (mid-level position)
        LocalDate secondJobStart = firstJob.getEndDate().plusMonths(1 + random.nextInt(3));
        WorkExperience secondJob = createWorkExperience(
                doctor,
                HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)],
                "Bác sĩ chuyên môn",
                secondJobStart,
                secondJobStart.plusYears(3 + random.nextInt(3))
        );
        experiences.add(secondJob);

        // Current job (senior position)
        LocalDate currentJobStart = secondJob.getEndDate().plusMonths(1 + random.nextInt(6));
        WorkExperience currentJob = createWorkExperience(
                doctor,
                HOSPITAL_NAMES[random.nextInt(HOSPITAL_NAMES.length)],
                "Bác sĩ điều trị",
                currentJobStart,
                null // Current position
        );
        experiences.add(currentJob);

        return experiences;
    }
}
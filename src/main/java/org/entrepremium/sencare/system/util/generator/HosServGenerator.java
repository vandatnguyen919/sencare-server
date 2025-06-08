package org.entrepremium.sencare.system.util.generator;

import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hospital.Hospital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HosServGenerator {
    
    private static final Random random = new Random();
    
    // Hospital service categories with Vietnamese names
    private static final List<ServiceData> MEDICAL_SERVICES = Arrays.asList(
        // General Medicine
        new ServiceData("Khám tổng quát", "Khám sức khỏe định kỳ và tư vấn y tế cơ bản cho mọi độ tuổi", 150000, 300000, "general_checkup.jpg"),
        new ServiceData("Khám nội khoa", "Chẩn đoán và điều trị các bệnh lý nội khoa như tiểu đường, cao huyết áp, tim mạch", 200000, 400000, "internal_medicine.jpg"),
        new ServiceData("Khám ngoại khoa", "Khám và điều trị các bệnh lý ngoại khoa, tư vấn phẫu thuật", 250000, 500000, "surgery_consultation.jpg"),
        
        // Specialized Departments
        new ServiceData("Khám tim mạch", "Siêu âm tim, điện tâm đồ, tư vấn điều trị bệnh tim mạch", 300000, 600000, "cardiology.jpg"),
        new ServiceData("Khám thần kinh", "Chẩn đoán các bệnh lý về thần kinh, đau đầu, đột quỵ", 350000, 700000, "neurology.jpg"),
        new ServiceData("Khám tiêu hóa", "Nội soi dạ dày, siêu âm bụng, điều trị bệnh dạ dày ruột", 280000, 550000, "gastroenterology.jpg"),
        new ServiceData("Khám hô hấp", "Chẩn đoán và điều trị các bệnh phổi, hen suyễn, viêm phế quản", 250000, 480000, "pulmonology.jpg"),
        new ServiceData("Khám thận - tiết niệu", "Siêu âm thận, xét nghiệm nước tiểu, điều trị sỏi thận", 320000, 650000, "urology.jpg"),
        
        // Pediatrics
        new ServiceData("Khám nhi khoa", "Khám sức khỏe trẻ em, tiêm chủng, tư vấn dinh dưỡng", 180000, 350000, "pediatrics.jpg"),
        new ServiceData("Tiêm chủng trẻ em", "Tiêm phòng các loại vaccine theo lịch tiêm chủng mở rộng", 100000, 250000, "vaccination.jpg"),
        
        // Women's Health
        new ServiceData("Khám phụ khoa", "Khám định kỳ phụ khoa, siêu âm phụ khoa, tư vấn kế hoạch hóa gia đình", 220000, 450000, "gynecology.jpg"),
        new ServiceData("Khám thai", "Siêu âm thai, theo dõi thai kỳ, tư vấn chăm sóc bà bầu", 250000, 500000, "obstetrics.jpg"),
        new ServiceData("Sinh đẻ", "Dịch vụ sinh đẻ an toàn với đội ngũ y bác sĩ chuyên nghiệp", 3000000, 8000000, "delivery.jpg"),
        
        // Orthopedics
        new ServiceData("Khám xương khớp", "Chẩn đoán và điều trị các bệnh lý xương khớp, cột sống", 280000, 560000, "orthopedics.jpg"),
        new ServiceData("Vật lý trị liệu", "Phục hồi chức năng sau chấn thương, điều trị đau nhức xương khớp", 150000, 300000, "physiotherapy.jpg"),
        
        // Diagnostic Services
        new ServiceData("Xét nghiệm máu", "Xét nghiệm tổng phân tích máu, sinh hóa máu, hormone", 100000, 500000, "blood_test.jpg"),
        new ServiceData("X-quang", "Chụp X-quang các bộ phận cơ thể để chẩn đoán bệnh", 80000, 200000, "xray.jpg"),
        new ServiceData("Siêu âm", "Siêu âm bụng, siêu âm tim, siêu âm tuyến giáp", 150000, 400000, "ultrasound.jpg"),
        new ServiceData("CT Scan", "Chụp cắt lớp vi tính để chẩn đoán chính xác các bệnh lý", 800000, 2000000, "ct_scan.jpg"),
        new ServiceData("MRI", "Chụp cộng hưởng từ để chẩn đoán chi tiết các bệnh lý", 1500000, 3500000, "mri.jpg"),
        
        // Emergency Services
        new ServiceData("Cấp cứu 24/7", "Dịch vụ cấp cứu khẩn cấp hoạt động 24 giờ mọi ngày trong tuần", 200000, 1000000, "emergency.jpg"),
        new ServiceData("Phẫu thuật cấp cứu", "Phẫu thuật khẩn cấp cứu sống bệnh nhân", 2000000, 10000000, "emergency_surgery.jpg"),
        
        // Dental Services
        new ServiceData("Khám răng hàm mặt", "Khám tổng quát răng miệng, tư vấn điều trị nha khoa", 100000, 200000, "dental_checkup.jpg"),
        new ServiceData("Nhổ răng", "Nhổ răng khôn, nhổ răng sâu, tiểu phẫu nha khoa", 150000, 500000, "tooth_extraction.jpg"),
        new ServiceData("Trám răng", "Trám răng sâu, phục hồi hình dạng và chức năng răng", 200000, 800000, "dental_filling.jpg"),
        new ServiceData("Làm răng giả", "Protez tháo lắp, cấy ghép implant, phục hồi răng mất", 1000000, 15000000, "dental_prosthetics.jpg"),
        
        // Eye Care
        new ServiceData("Khám mắt", "Khám tật khúc xạ, đo thị lực, tư vấn đeo kính", 120000, 250000, "eye_exam.jpg"),
        new ServiceData("Phẫu thuật mắt", "Phẫu thuật cận thị, đục thủy tinh thể, glaucoma", 5000000, 20000000, "eye_surgery.jpg"),
        
        // ENT Services
        new ServiceData("Khám tai mũi họng", "Chẩn đoán và điều trị các bệnh lý tai mũi họng", 180000, 350000, "ent.jpg"),
        new ServiceData("Phẫu thuật TMH", "Phẫu thuật polyp mũi, amidan, cuống họng", 2000000, 8000000, "ent_surgery.jpg"),
        
        // Mental Health
        new ServiceData("Tư vấn tâm lý", "Tư vấn sức khỏe tâm thần, điều trị stress, trầm cảm", 300000, 600000, "psychology.jpg"),
        new ServiceData("Khám tâm thần", "Chẩn đoán và điều trị các rối loạn tâm thần", 400000, 800000, "psychiatry.jpg"),
        
        // Dermatology
        new ServiceData("Khám da liễu", "Điều trị mụn trứng cá, bệnh nấm da, dị ứng da", 200000, 400000, "dermatology.jpg"),
        new ServiceData("Thẩm mỹ da", "Điều trị sẹo, nám, tàn nhang bằng công nghệ hiện đại", 500000, 2000000, "cosmetic_dermatology.jpg"),
        
        // Rehabilitation
        new ServiceData("Phục hồi chức năng", "Điều trị phục hồi sau tai biến, chấn thương", 200000, 500000, "rehabilitation.jpg"),
        new ServiceData("Massage y học", "Massage trị liệu giảm đau nhức cơ bắp, xương khớp", 150000, 300000, "medical_massage.jpg"),
        
        // Health Screening
        new ServiceData("Gói khám sức khỏe tổng quát", "Khám tổng quát toàn diện bao gồm các xét nghiệm cơ bản", 800000, 2000000, "health_package.jpg"),
        new ServiceData("Tầm soát ung thư", "Sàng lọc các loại ung thư phổ biến bằng các xét nghiệm chuyên sâu", 1500000, 5000000, "cancer_screening.jpg")
    );
    
    // Service image templates
    private static final List<String> SERVICE_IMAGES = Arrays.asList(
        "service_1.jpg", "service_2.jpg", "service_3.jpg", "service_4.jpg", 
        "service_5.jpg", "medical_service.jpg", "hospital_service.jpg",
        "healthcare_service.jpg", "clinic_service.jpg", "treatment_service.jpg"
    );
    
    /**
     * Generate a single random hospital service
     */
    public static HosServ generateHospitalService(Hospital hospital) {
        ServiceData serviceData = getRandomFromList(MEDICAL_SERVICES);
        
        HosServ hosServ = new HosServ();
        hosServ.setHospital(hospital);
        hosServ.setServName(serviceData.getName());
        hosServ.setServDesc(serviceData.getDescription());
        hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice()));
        hosServ.setServImage(serviceData.getImageName());
        hosServ.setAvailable(generateAvailabilityStatus());
        
        return hosServ;
    }
    
    /**
     * Generate multiple hospital services
     */
    public static List<HosServ> generateHospitalServices(Hospital hospital, int count) {
        List<HosServ> services = new ArrayList<>();
        List<ServiceData> availableServices = new ArrayList<>(MEDICAL_SERVICES);
        
        for (int i = 0; i < Math.min(count, availableServices.size()); i++) {
            ServiceData serviceData = availableServices.remove(random.nextInt(availableServices.size()));
            
            HosServ hosServ = new HosServ();
            hosServ.setHospital(hospital);
            hosServ.setServName(serviceData.getName());
            hosServ.setServDesc(serviceData.getDescription());
            hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice()));
            hosServ.setServImage(serviceData.getImageName());
            hosServ.setAvailable(generateAvailabilityStatus());
            
            services.add(hosServ);
        }
        
        return services;
    }
    
    /**
     * Generate essential hospital services (common services every hospital should have)
     */
    public static List<HosServ> generateEssentialHospitalServices(Hospital hospital) {
        List<String> essentialServices = Arrays.asList(
            "Khám tổng quát", "Cấp cứu 24/7", "Xét nghiệm máu", "X-quang", 
            "Khám nội khoa", "Khám nhi khoa", "Khám phụ khoa"
        );
        
        List<HosServ> services = new ArrayList<>();
        
        for (String serviceName : essentialServices) {
            ServiceData serviceData = MEDICAL_SERVICES.stream()
                    .filter(s -> s.getName().equals(serviceName))
                    .findFirst()
                    .orElse(MEDICAL_SERVICES.get(0));
            
            HosServ hosServ = new HosServ();
            hosServ.setHospital(hospital);
            hosServ.setServName(serviceData.getName());
            hosServ.setServDesc(serviceData.getDescription());
            hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice()));
            hosServ.setServImage(serviceData.getImageName());
            hosServ.setAvailable(true); // Essential services are always available
            
            services.add(hosServ);
        }
        
        return services;
    }
    
    /**
     * Generate specialized services based on hospital type
     */
    public static List<HosServ> generateSpecializedServices(Hospital hospital, String specialty) {
        List<HosServ> services = new ArrayList<>();
        List<ServiceData> specializedServices = new ArrayList<>();
        
        switch (specialty.toLowerCase()) {
            case "cardiology":
            case "tim mạch":
                specializedServices = MEDICAL_SERVICES.stream()
                        .filter(s -> s.getName().contains("tim mạch") || s.getName().contains("tim"))
                        .toList();
                break;
            case "pediatrics":
            case "nhi khoa":
                specializedServices = MEDICAL_SERVICES.stream()
                        .filter(s -> s.getName().contains("nhi") || s.getName().contains("trẻ em"))
                        .toList();
                break;
            case "obstetrics":
            case "phụ sản":
                specializedServices = MEDICAL_SERVICES.stream()
                        .filter(s -> s.getName().contains("phụ khoa") || s.getName().contains("thai") || s.getName().contains("sinh"))
                        .toList();
                break;
            case "orthopedics":
            case "xương khớp":
                specializedServices = MEDICAL_SERVICES.stream()
                        .filter(s -> s.getName().contains("xương khớp") || s.getName().contains("vật lý"))
                        .toList();
                break;
            case "emergency":
            case "cấp cứu":
                specializedServices = MEDICAL_SERVICES.stream()
                        .filter(s -> s.getName().contains("cấp cứu"))
                        .toList();
                break;
            default:
                specializedServices = MEDICAL_SERVICES.subList(0, 5); // Random selection
        }
        
        for (ServiceData serviceData : specializedServices) {
            HosServ hosServ = new HosServ();
            hosServ.setHospital(hospital);
            hosServ.setServName(serviceData.getName());
            hosServ.setServDesc(serviceData.getDescription());
            hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice()));
            hosServ.setServImage(serviceData.getImageName());
            hosServ.setAvailable(generateAvailabilityStatus());
            
            services.add(hosServ);
        }
        
        return services;
    }
    
    /**
     * Generate premium services (high-end services)
     */
    public static List<HosServ> generatePremiumServices(Hospital hospital) {
        List<ServiceData> premiumServices = MEDICAL_SERVICES.stream()
                .filter(s -> s.getMaxPrice() > 1000000) // Services over 1 million VND
                .toList();
        
        List<HosServ> services = new ArrayList<>();
        int serviceCount = Math.min(5, premiumServices.size());
        
        for (int i = 0; i < serviceCount; i++) {
            ServiceData serviceData = premiumServices.get(i);
            
            HosServ hosServ = new HosServ();
            hosServ.setHospital(hospital);
            hosServ.setServName(serviceData.getName() + " (Cao cấp)");
            hosServ.setServDesc(serviceData.getDescription() + " Dịch vụ cao cấp với trang thiết bị hiện đại nhất.");
            hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice()));
            hosServ.setServImage(serviceData.getImageName());
            hosServ.setAvailable(generateAvailabilityStatus());
            
            services.add(hosServ);
        }
        
        return services;
    }
    
    /**
     * Generate budget-friendly services
     */
    public static List<HosServ> generateBudgetServices(Hospital hospital) {
        List<ServiceData> budgetServices = MEDICAL_SERVICES.stream()
                .filter(s -> s.getMaxPrice() <= 500000) // Services under 500K VND
                .toList();
        
        List<HosServ> services = new ArrayList<>();
        
        for (ServiceData serviceData : budgetServices) {
            HosServ hosServ = new HosServ();
            hosServ.setHospital(hospital);
            hosServ.setServName(serviceData.getName());
            hosServ.setServDesc(serviceData.getDescription() + " Dịch vụ chất lượng với giá cả phải chăng.");
            hosServ.setServPrice(generateRandomPrice(serviceData.getMinPrice(), serviceData.getMaxPrice() * 0.8)); // 20% discount
            hosServ.setServImage(serviceData.getImageName());
            hosServ.setAvailable(generateAvailabilityStatus());
            
            services.add(hosServ);
        }
        
        return services;
    }
    
    /**
     * Generate random price within range
     */
    private static double generateRandomPrice(double minPrice, double maxPrice) {
        double price = minPrice + (random.nextDouble() * (maxPrice - minPrice));
        // Round to nearest 10,000 VND
        return Math.round(price / 10000.0) * 10000.0;
    }
    
    /**
     * Generate availability status (90% available)
     */
    private static boolean generateAvailabilityStatus() {
        return random.nextDouble() < 0.9;
    }
    
    /**
     * Get random element from list
     */
    private static <T> T getRandomFromList(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
    
    /**
     * Inner class to hold service data
     */
    private static class ServiceData {
        private final String name;
        private final String description;
        private final double minPrice;
        private final double maxPrice;
        private final String imageName;
        
        public ServiceData(String name, String description, double minPrice, double maxPrice, String imageName) {
            this.name = name;
            this.description = description;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.imageName = imageName;
        }
        
        public String getName() { return name; }
        public String getDescription() { return description; }
        public double getMinPrice() { return minPrice; }
        public double getMaxPrice() { return maxPrice; }
        public String getImageName() { return imageName; }
    }
}
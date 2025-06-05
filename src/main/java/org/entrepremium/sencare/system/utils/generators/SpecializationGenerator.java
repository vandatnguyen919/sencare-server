package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.specialization.Specialization;

import java.util.ArrayList;
import java.util.List;

public class SpecializationGenerator {

    private static final String[][] SPECIALIZATIONS = {
            {"Tim mạch", "Chẩn đoán và điều trị các rối loạn tim và hệ thống tim mạch"},
            {"Thần kinh", "Điều trị các rối loạn của hệ thần kinh bao gồm não và tủy sống"},
            {"Chỉnh hình", "Điều trị hệ thống cơ xương khớp bao gồm xương, khớp và cơ"},
            {"Nhi khoa", "Chăm sóc y tế cho trẻ sơ sinh, trẻ em và thanh thiếu niên"},
            {"Ung bướu", "Chẩn đoán và điều trị ung thư và các khối u ác tính"},
            {"Da liễu", "Điều trị các rối loạn về da, tóc và móng"},
            {"Tiêu hóa", "Điều trị các rối loạn hệ tiêu hóa và gan"},
            {"Hô hấp", "Điều trị hệ hô hấp và các bệnh phổi"},
            {"Nội tiết", "Điều trị các rối loạn hormone và hệ nội tiết"},
            {"Tâm thần", "Chẩn đoán và điều trị các rối loạn sức khỏe tâm thần"},
            {"Tiết niệu", "Điều trị đường tiết niệu và hệ sinh dục nam"},
            {"Mắt", "Điều trị các rối loạn về mắt và thị lực"},
            {"Tai mũi họng", "Điều trị các rối loạn tai, mũi và họng"},
            {"Chẩn đoán hình ảnh", "Chụp ảnh y tế và các thủ thuật chẩn đoán"},
            {"Gây mê hồi sức", "Quản lý đau và dịch vụ gây mê"},
            {"Cấp cứu", "Chăm sóc y tế tức thời cho chấn thương và bệnh tình cấp tính"},
            {"Nội khoa", "Chăm sóc toàn diện cho các tình trạng y tế người lớn"},
            {"Đa khoa", "Chăm sóc sức khỏe ban đầu cho bệnh nhân ở mọi lứa tuổi"},
            {"Phụ sản", "Sức khỏe phụ nữ, mang thai và chăm sóc sinh nở"},
            {"Thấp khớp", "Điều trị các bệnh tự miễn và viêm"},
            {"Bệnh nhiễm trùng", "Chẩn đoán và điều trị nhiễm trùng và bệnh truyền nhiễm"},
            {"Thận", "Điều trị các bệnh và rối loạn thận"},
            {"Huyết học", "Điều trị các rối loạn và bệnh về máu"},
            {"Phục hồi chức năng", "Dịch vụ phục hồi chức năng và vật lý trị liệu"},
            {"Giải phẫu bệnh", "Chẩn đoán phòng thí nghiệm và phân tích bệnh"},
            {"Phẫu thuật thẩm mỹ", "Các thủ thuật phẫu thuật tái tạo và thẩm mỹ"},
            {"Phẫu thuật tổng quát", "Điều trị phẫu thuật các tình trạng y tế khác nhau"},
            {"Phẫu thuật mạch máu", "Điều trị phẫu thuật các rối loạn mạch máu"},
            {"Phẫu thuật thần kinh", "Điều trị phẫu thuật các rối loạn hệ thần kinh"},
            {"Phẫu thuật tim", "Điều trị phẫu thuật tim và các tình trạng tim mạch"}
    };

    public static List<Specialization> generateSampleSpecializations() {
        List<Specialization> specializations = new ArrayList<>();

        for (String[] specData : SPECIALIZATIONS) {
            Specialization specialization = new Specialization();
            specialization.setSpecName(specData[0]);
            specialization.setSpecDescription(specData[1]);
            specializations.add(specialization);
        }

        return specializations;
    }

    public static Specialization createSpecialization(String name, String description) {
        Specialization specialization = new Specialization();
        specialization.setSpecName(name);
        specialization.setSpecDescription(description);
        return specialization;
    }

    public static List<Specialization> getCommonSpecializations() {
        List<Specialization> commonSpecs = new ArrayList<>();

        // Return the most common hospital specializations
        String[] commonSpecNames = {
                "Cấp cứu", "Nội khoa", "Đa khoa",
                "Tim mạch", "Chỉnh hình", "Nhi khoa", "Phẫu thuật tổng quát",
                "Chẩn đoán hình ảnh", "Gây mê hồi sức", "Phụ sản"
        };

        for (String specName : commonSpecNames) {
            for (String[] specData : SPECIALIZATIONS) {
                if (specData[0].equals(specName)) {
                    Specialization spec = new Specialization();
                    spec.setSpecName(specData[0]);
                    spec.setSpecDescription(specData[1]);
                    commonSpecs.add(spec);
                    break;
                }
            }
        }

        return commonSpecs;
    }
}
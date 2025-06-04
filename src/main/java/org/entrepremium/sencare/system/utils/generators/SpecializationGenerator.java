package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.feature.specialization.Specialization;

import java.util.ArrayList;
import java.util.List;

public class SpecializationGenerator {

    private static final String[][] SPECIALIZATIONS = {
            {"Cardiology", "Diagnosis and treatment of heart and cardiovascular system disorders"},
            {"Neurology", "Treatment of disorders of the nervous system including brain and spinal cord"},
            {"Orthopedics", "Treatment of musculoskeletal system including bones, joints, and muscles"},
            {"Pediatrics", "Medical care for infants, children, and adolescents"},
            {"Oncology", "Diagnosis and treatment of cancer and malignant tumors"},
            {"Dermatology", "Treatment of skin, hair, and nail disorders"},
            {"Gastroenterology", "Treatment of digestive system and liver disorders"},
            {"Pulmonology", "Treatment of respiratory system and lung diseases"},
            {"Endocrinology", "Treatment of hormone and endocrine system disorders"},
            {"Psychiatry", "Diagnosis and treatment of mental health disorders"},
            {"Urology", "Treatment of urinary tract and male reproductive system disorders"},
            {"Ophthalmology", "Treatment of eye and vision disorders"},
            {"Otolaryngology", "Treatment of ear, nose, and throat disorders"},
            {"Radiology", "Medical imaging and diagnostic procedures"},
            {"Anesthesiology", "Pain management and anesthesia services"},
            {"Emergency Medicine", "Immediate medical care for acute injuries and illnesses"},
            {"Internal Medicine", "Comprehensive care for adult medical conditions"},
            {"Family Medicine", "Primary healthcare for patients of all ages"},
            {"Obstetrics & Gynecology", "Women's health, pregnancy, and childbirth care"},
            {"Rheumatology", "Treatment of autoimmune and inflammatory diseases"},
            {"Infectious Diseases", "Diagnosis and treatment of infections and communicable diseases"},
            {"Nephrology", "Treatment of kidney diseases and disorders"},
            {"Hematology", "Treatment of blood disorders and diseases"},
            {"Physical Medicine", "Rehabilitation and physical therapy services"},
            {"Pathology", "Laboratory diagnosis and disease analysis"},
            {"Plastic Surgery", "Reconstructive and cosmetic surgical procedures"},
            {"General Surgery", "Surgical treatment of various medical conditions"},
            {"Vascular Surgery", "Surgical treatment of blood vessel disorders"},
            {"Neurosurgery", "Surgical treatment of nervous system disorders"},
            {"Cardiac Surgery", "Surgical treatment of heart and cardiovascular conditions"}
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
                "Emergency Medicine", "Internal Medicine", "Family Medicine",
                "Cardiology", "Orthopedics", "Pediatrics", "General Surgery",
                "Radiology", "Anesthesiology", "Obstetrics & Gynecology"
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
package org.entrepremium.sencare.system.utils.generators;

import org.entrepremium.sencare.features.hospital.Hospital;
import org.entrepremium.sencare.features.hospitalspec.HospitalSpec;
import org.entrepremium.sencare.features.specialization.Specialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HospitalSpecGenerator {

    public static List<HospitalSpec> generateSampleHospitalSpecs(List<Hospital> hospitals,
                                                                 List<Specialization> specializations) {
        List<HospitalSpec> hospitalSpecs = new ArrayList<>();
        Random random = new Random();

        for (Hospital hospital : hospitals) {
            // Each hospital gets 3-8 random specializations
            int numSpecs = 3 + random.nextInt(6);

            // Shuffle specializations to get random selection
            List<Specialization> shuffledSpecs = new ArrayList<>(specializations);
            Collections.shuffle(shuffledSpecs);

            // Assign first numSpecs specializations to this hospital
            for (int i = 0; i < numSpecs && i < shuffledSpecs.size(); i++) {
                HospitalSpec hospitalSpec = new HospitalSpec();
                hospitalSpec.setHospital(hospital);
                hospitalSpec.setSpecialization(shuffledSpecs.get(i));
                hospitalSpecs.add(hospitalSpec);
            }
        }

        return hospitalSpecs;
    }

    public static List<HospitalSpec> generateHospitalSpecsForHospital(Hospital hospital,
                                                                      List<Specialization> availableSpecs) {
        List<HospitalSpec> hospitalSpecs = new ArrayList<>();
        Random random = new Random();

        // Determine number of specializations for this hospital (3-6)
        int numSpecs = 3 + random.nextInt(4);

        // Create a copy of available specializations and shuffle
        List<Specialization> shuffledSpecs = new ArrayList<>(availableSpecs);
        Collections.shuffle(shuffledSpecs);

        // Assign specializations
        for (int i = 0; i < numSpecs && i < shuffledSpecs.size(); i++) {
            HospitalSpec hospitalSpec = new HospitalSpec();
            hospitalSpec.setHospital(hospital);
            hospitalSpec.setSpecialization(shuffledSpecs.get(i));
            hospitalSpecs.add(hospitalSpec);
        }

        return hospitalSpecs;
    }

    public static HospitalSpec createHospitalSpec(Hospital hospital, Specialization specialization) {
        HospitalSpec hospitalSpec = new HospitalSpec();
        hospitalSpec.setHospital(hospital);
        hospitalSpec.setSpecialization(specialization);
        return hospitalSpec;
    }

    public static List<HospitalSpec> generateCommonHospitalSpecs(List<Hospital> hospitals,
                                                                 List<Specialization> specializations) {
        List<HospitalSpec> hospitalSpecs = new ArrayList<>();

        // Ensure all hospitals have common essential specializations
        String[] essentialSpecs = {"Emergency Medicine", "Internal Medicine", "General Surgery"};

        for (Hospital hospital : hospitals) {
            // Add essential specializations
            for (String essentialSpecName : essentialSpecs) {
                Specialization essentialSpec = specializations.stream()
                        .filter(spec -> spec.getSpecName().equals(essentialSpecName))
                        .findFirst()
                        .orElse(null);

                if (essentialSpec != null) {
                    HospitalSpec hospitalSpec = createHospitalSpec(hospital, essentialSpec);
                    hospitalSpecs.add(hospitalSpec);
                }
            }

            // Add 2-4 additional random specializations
            Random random = new Random();
            List<Specialization> remainingSpecs = new ArrayList<>(specializations);
            remainingSpecs.removeIf(spec ->
                    spec.getSpecName().equals("Emergency Medicine") ||
                            spec.getSpecName().equals("Internal Medicine") ||
                            spec.getSpecName().equals("General Surgery")
            );

            Collections.shuffle(remainingSpecs);
            int additionalSpecs = 2 + random.nextInt(3);

            for (int i = 0; i < additionalSpecs && i < remainingSpecs.size(); i++) {
                HospitalSpec hospitalSpec = createHospitalSpec(hospital, remainingSpecs.get(i));
                hospitalSpecs.add(hospitalSpec);
            }
        }

        return hospitalSpecs;
    }
}
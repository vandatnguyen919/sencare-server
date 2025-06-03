package org.entrepremium.sencare.feature.hospitalsystem.hospital;

import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital findById(String hospitalId) {
        return hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ObjectNotFoundException("hospital", hospitalId));
    }
}

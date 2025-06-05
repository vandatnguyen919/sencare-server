package org.entrepremium.sencare.feature.hosserv;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.system.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HosServService {

    private final HosServRepository hosServRepository;

    public Page<HosServ> findAll(Pageable pageable) {
        return hosServRepository.findAll(pageable);
    }

    public HosServ findById(String hosServId) {
        return hosServRepository.findById(hosServId)
                .orElseThrow(() -> new ObjectNotFoundException("hospital service", hosServId));
    }

    public HosServ save(HosServ newHosServ) {
        return hosServRepository.save(newHosServ);
    }

    public HosServ update(String hosServId, HosServ hosServ) {
        return hosServRepository.findById(hosServId)
                .map(oldHosServ -> {
                    oldHosServ.setServImage(hosServ.getServImage());
                    oldHosServ.setServName(hosServ.getServName());
                    oldHosServ.setServDesc(hosServ.getServDesc());
                    oldHosServ.setServPrice(hosServ.getServPrice());
                    oldHosServ.setAvailable(hosServ.isAvailable());
                    return hosServRepository.save(oldHosServ);
                })
                .orElseThrow(() -> new ObjectNotFoundException("hospital service", hosServId));
    }

    public void delete(String hosServId) {
        this.hosServRepository.findById(hosServId)
                .orElseThrow(() -> new ObjectNotFoundException("hospital service", hosServId));
        this.hosServRepository.deleteById(hosServId);
    }
}
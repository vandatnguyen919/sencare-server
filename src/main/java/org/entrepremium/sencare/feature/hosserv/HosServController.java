package org.entrepremium.sencare.feature.hosserv;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hospital.HospitalService;
import org.entrepremium.sencare.feature.hosserv.converter.HosServDtoToHosServConverter;
import org.entrepremium.sencare.feature.hosserv.converter.HosServToHosServDtoConverter;
import org.entrepremium.sencare.feature.hosserv.dto.HosServDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/hosserv")
@RequiredArgsConstructor
public class HosServController {

    private final HosServService hosServService;
    private final HospitalService hospitalService;
    private final HosServToHosServDtoConverter hosServToHosServDtoConverter;
    private final HosServDtoToHosServConverter hosServDtoToHosServConverter;

    @GetMapping
    public Result getAllHosServ(Pageable pageable) {
        Page<HosServ> hosServPage = hosServService.findAll(pageable);
        Page<HosServDto> hosServDtoPage = hosServPage.map(hosServToHosServDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", hosServDtoPage);
    }

    @GetMapping("/{hosServId}")
    public Result getHosServById(@PathVariable String hosServId) {
        HosServ hosServ = hosServService.findById(hosServId);
        HosServDto hosServDto = hosServToHosServDtoConverter.convert(hosServ);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", hosServDto);
    }

    @PostMapping("/hospitals/{hospitalId}")
    public Result addHosServ(@PathVariable String hospitalId, @Valid @RequestBody HosServ newHosServ) {
        Hospital hospital = hospitalService.findById(hospitalId);
        newHosServ.setHospital(hospital);
        HosServ savedHosServ = hosServService.save(newHosServ);
        HosServDto savedHosServDto = hosServToHosServDtoConverter.convert(savedHosServ);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedHosServDto);
    }

    @PutMapping("/{hosServId}")
    public Result updateHosServ(@PathVariable String hosServId, @Valid @RequestBody HosServDto hosServDto) {
        HosServ update = hosServDtoToHosServConverter.convert(hosServDto);
        HosServ updatedHosServ = hosServService.update(hosServId, update);
        HosServDto updatedHosServDto = hosServToHosServDtoConverter.convert(updatedHosServ);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedHosServDto);
    }

    @DeleteMapping("/{hosServId}")
    public Result deleteHosServ(@PathVariable String hosServId) {
        this.hosServService.delete(hosServId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }
}
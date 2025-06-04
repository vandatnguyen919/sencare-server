package org.entrepremium.sencare.features.hospital;

import org.entrepremium.sencare.features.hospital.converter.HospitalToDtoConverter;
import org.entrepremium.sencare.features.hospital.dto.HospitalDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    private final HospitalToDtoConverter hospitalToDtoConverter;

    public HospitalController(HospitalService hospitalService,
                              HospitalToDtoConverter hospitalToDtoConverter) {
        this.hospitalService = hospitalService;
        this.hospitalToDtoConverter = hospitalToDtoConverter;
    }

    @GetMapping
    public Result findAllHospitals(Pageable pageable) {
        Page<Hospital> foundHospitals = hospitalService.findAll(pageable);
        Page<HospitalDto> hospitalDtos = foundHospitals.map(hospitalToDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Hospitals Success", hospitalDtos);
    }

    @GetMapping("/{hospitalId}")
    public Result findHospitalById(@PathVariable String hospitalId) {
        Hospital foundHospital = hospitalService.findById(hospitalId);
        HospitalDto hospitalDto = hospitalToDtoConverter.convert(foundHospital);
        return new Result(true, StatusCode.SUCCESS, "Find One Hospital Success", hospitalDto);
    }

    @GetMapping("/search-name")
    public Result searchHospitalsByName(@RequestParam String name) {
        List<Hospital> foundHospitals = hospitalService.findByName(name);
        List<HospitalDto> hospitalDtos = foundHospitals.stream()
                .map(hospitalToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Hospitals By Name Success", hospitalDtos);
    }

    @PostMapping
    public Result addHospital(@RequestBody Hospital newHospital) {
        Hospital savedHospital = hospitalService.save(newHospital);
        HospitalDto savedHospitalDto = hospitalToDtoConverter.convert(savedHospital);
        return new Result(true, StatusCode.SUCCESS, "Add Hospital Success", savedHospitalDto);
    }

    @PutMapping("/{hospitalId}")
    public Result updateHospital(@PathVariable String hospitalId, @RequestBody Hospital update) {
        Hospital updatedHospital = hospitalService.update(hospitalId, update);
        HospitalDto updatedHospitalDto = hospitalToDtoConverter.convert(updatedHospital);
        return new Result(true, StatusCode.SUCCESS, "Update Hospital Success", updatedHospitalDto);
    }

    @DeleteMapping("/{hospitalId}")
    public Result deleteHospital(@PathVariable String hospitalId) {
        hospitalService.delete(hospitalId);
        return new Result(true, StatusCode.SUCCESS, "Delete Hospital Success");
    }
}

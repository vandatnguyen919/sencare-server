package org.entrepremium.sencare.features.hospitalspec;

import org.entrepremium.sencare.features.hospitalspec.converter.HospitalSpecToDtoConverter;
import org.entrepremium.sencare.features.hospitalspec.dto.HospitalSpecDto;
import org.entrepremium.sencare.system.Result;
import org.entrepremium.sencare.system.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/hospital-specs")
public class HospitalSpecController {
    private final HospitalSpecService hospitalSpecService;
    private final HospitalSpecToDtoConverter hospitalSpecToDtoConverter;

    public HospitalSpecController(HospitalSpecService hospitalSpecService,
                                  HospitalSpecToDtoConverter hospitalSpecToDtoConverter) {
        this.hospitalSpecService = hospitalSpecService;
        this.hospitalSpecToDtoConverter = hospitalSpecToDtoConverter;
    }

    @GetMapping
    public Result findAllHospitalSpecs(Pageable pageable) {
        Page<HospitalSpec> foundHospitalSpecs = hospitalSpecService.findAll(pageable);
        Page<HospitalSpecDto> hospitalSpecDtos = foundHospitalSpecs.map(hospitalSpecToDtoConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Hospital Specializations Success", hospitalSpecDtos);
    }

    @GetMapping("/{id}")
    public Result findHospitalSpecById(@PathVariable String id) {
        HospitalSpec foundHospitalSpec = hospitalSpecService.findById(id);
        HospitalSpecDto hospitalSpecDto = hospitalSpecToDtoConverter.convert(foundHospitalSpec);
        return new Result(true, StatusCode.SUCCESS, "Find One Hospital Specialization Success", hospitalSpecDto);
    }

    @GetMapping("/by-hospital/{hospitalId}")
    public Result findHospitalSpecsByHospitalId(@PathVariable String hospitalId) {
        List<HospitalSpec> foundHospitalSpecs = hospitalSpecService.findByHospitalId(hospitalId);
        List<HospitalSpecDto> hospitalSpecDtos = foundHospitalSpecs.stream()
                .map(hospitalSpecToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Hospital Specializations By Hospital Success", hospitalSpecDtos);
    }

    @GetMapping("/by-specialization/{specId}")
    public Result findHospitalSpecsBySpecializationId(@PathVariable String specId) {
        List<HospitalSpec> foundHospitalSpecs = hospitalSpecService.findBySpecializationId(specId);
        List<HospitalSpecDto> hospitalSpecDtos = foundHospitalSpecs.stream()
                .map(hospitalSpecToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find Hospital Specializations By Specialization Success", hospitalSpecDtos);
    }

    @GetMapping("/search-hospital")
    public Result searchHospitalSpecsByHospitalName(@RequestParam String hospitalName) {
        List<HospitalSpec> foundHospitalSpecs = hospitalSpecService.findByHospitalName(hospitalName);
        List<HospitalSpecDto> hospitalSpecDtos = foundHospitalSpecs.stream()
                .map(hospitalSpecToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Hospital Specializations By Hospital Name Success", hospitalSpecDtos);
    }

    @GetMapping("/search-specialization")
    public Result searchHospitalSpecsBySpecializationName(@RequestParam String specName) {
        List<HospitalSpec> foundHospitalSpecs = hospitalSpecService.findBySpecializationName(specName);
        List<HospitalSpecDto> hospitalSpecDtos = foundHospitalSpecs.stream()
                .map(hospitalSpecToDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Hospital Specializations By Specialization Name Success", hospitalSpecDtos);
    }

    @PostMapping
    public Result addHospitalSpec(@RequestBody HospitalSpec newHospitalSpec) {
        HospitalSpec savedHospitalSpec = hospitalSpecService.save(newHospitalSpec);
        HospitalSpecDto savedHospitalSpecDto = hospitalSpecToDtoConverter.convert(savedHospitalSpec);
        return new Result(true, StatusCode.SUCCESS, "Add Hospital Specialization Success", savedHospitalSpecDto);
    }

    @PutMapping("/{id}")
    public Result updateHospitalSpec(@PathVariable String id, @RequestBody HospitalSpec update) {
        HospitalSpec updatedHospitalSpec = hospitalSpecService.update(id, update);
        HospitalSpecDto updatedHospitalSpecDto = hospitalSpecToDtoConverter.convert(updatedHospitalSpec);
        return new Result(true, StatusCode.SUCCESS, "Update Hospital Specialization Success", updatedHospitalSpecDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteHospitalSpec(@PathVariable String id) {
        hospitalSpecService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Delete Hospital Specialization Success");
    }
}
